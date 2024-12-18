#!/bin/bash

# Функция для получения данных о процессе
get_process_info() {
    local pid="$1"  # PID процесса
    local indent="$2"  # Отступ для форматирования (рекурсивный вывод)

    # Проверяем существование каталога процесса
    if [ ! -d "/proc/$pid" ]; then
        echo "${indent}Процесс с PID $pid не найден или нет доступа."
        return
    fi

    # Название утилиты
    local name=$(cat "/proc/$pid/comm" 2>/dev/null || echo "[неизвестно]")
    echo "${indent}PID = $pid"
    echo "${indent}Название утилиты: $name"

    # Открытые файловые дескрипторы
    echo "${indent}Открытые файловые дескрипторы:"
    if ls "/proc/$pid/fd" &>/dev/null; then
        ls -l "/proc/$pid/fd" 2>/dev/null | awk -v indent="$indent" '{if (NR>1) print indent "  " $9 " -> " $11}'
    else
        echo "${indent}  [нет данных]"
    fi

    # Дочерние процессы
    echo "${indent}Дочерние процессы:"
    local children=$(grep -l "^PPid:\s*$pid" /proc/*/status 2>/dev/null | grep -oP "/proc/\\d+" | grep -oP "\\d+")

    if [ -z "$children" ]; then
        echo "${indent}  [нет дочерних процессов]"
    else
        for child_pid in $children; do
            echo "${indent}  PID дочернего процесса: $child_pid"
        done
        # Рекурсивно вызываем для дочерних процессов
        for child_pid in $children; do
            get_process_info "$child_pid" "${indent}    "
        done
    fi
}

# Проверка аргументов
if [ $# -ne 1 ]; then
    echo "Использование: $0 <PID>"
    exit 1
fi

# Проверяем, что PID — это число
if ! [[ $1 =~ ^[0-9]+$ ]]; then
    echo "Ошибка: PID должен быть числом."
    exit 1
fi

# Запуск функции с начальным PID
get_process_info "$1" ""
