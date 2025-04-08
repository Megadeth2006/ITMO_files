import plotly.graph_objects as go

import pandas as pd
from datetime import datetime
import csv

df = pd.read_csv('file.csv')
    
# print(df)


fig = go.Figure(data=[go.Candlestick(x=df['<DATE>'],
                open=df['<OPEN>'],
                high=df['<HIGH>'],
                low=df['<LOW>'],
                close=df['<CLOSE>'])])

fig.show()