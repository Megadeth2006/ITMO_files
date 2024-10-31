import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Create dataframe from the data
data = {
    'Дата опроса': ['15.04.1994', '20.05.1994', '25.06.1994', '06.09.1994', '16.11.1994', 
                    '10.01.1995', '20.02.1995', '30.03.1995', '20.04.1995', '30.10.1996',
                    '15.01.1998', '30.03.1999', '15.04.1999', '15.06.1999', '30.01.2000'],
    'Одобряю': [34, 27, 31, 30, 31, 19, 15, 14, 15, 36, 25, 8, 8, 7, 15],
    'Не одобряю': [66, 73, 69, 70, 69, 81, 85, 86, 85, 64, 75, 92, 92, 93, 85]
}

df = pd.DataFrame(data)
df['Дата опроса'] = pd.to_datetime(df['Дата опроса'], format='%d.%m.%Y')

# Create the plot
plt.figure(figsize=(12, 6))
plt.plot(df['Дата опроса'], df['Одобряю'], color='green', label='Одобряю', marker='o')
plt.plot(df['Дата опроса'], df['Не одобряю'], color='red', label='Не одобряю', marker='o')

# Customize the plot
plt.title('Вы одобряете или не одобряете то, как Борис Ельцин\nсправляется с обязанностями президента России?')
plt.xlabel('Дата опроса')
plt.ylabel('Процент голосов')
plt.grid(True, linestyle='--', alpha=0.9)
plt.legend()

# Rotate x-axis labels for better readability
plt.xticks(rotation=45)

# Adjust layout to prevent label cutoff
plt.tight_layout()

plt.show()