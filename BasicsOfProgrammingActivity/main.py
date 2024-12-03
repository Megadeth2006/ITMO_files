### Бот для получения всех вариаций текста по запросу из варианта ### тупо по фану написал

import requests
import time
# URL для запроса
url = "https://se.ifmo.ru/courses/software-engineering-basics?p_p_id=selab1_WAR_seportlet&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&_selab1_WAR_seportlet_javax.portlet.action=getTask&p_auth=ZbirKArI"

# Заголовки (Headers)
headers = {
    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
    "Cookie": "_ga=GA1.2.599239031.1733230199; _gid=GA1.2.440362133.1733230199; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=ru_RU; JSESSIONID=KSStJzaySh_vbDgI3_YpPC6v-I_SlXj-daAsGpAy.lportal; _ga_5R1ZXK9D1L=GS1.2.1733230200.1.1.1733230386.57.0.0; LFR_SESSION_STATE_10158=1733230386670",
    "Origin": "https://se.ifmo.ru",
    "Referer": "https://se.ifmo.ru/courses/software-engineering-basics",
    "X-Requested-With": "XMLHttpRequest",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
}
for i in range(0, 100):
    form_data = {"var": str(i)}  
    response = requests.post(url, headers=headers, data=form_data)

    if response.status_code == 200:
        print("Ответ сервера:", response.text)  
    time.sleep(1)
