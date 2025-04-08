from datetime import date
import plotly.graph_objs as go

import numpy as np
import pandas as pd
import csv

# candle chart построить

with open('data.csv') as file:  
    
    reader = csv.reader(file, delimiter=",", quotechar='"')
    next(reader, None)
    
    data_read = [row for row in reader]

d_open  = [[], [] ,[], []]
d_high  = [[], [] ,[], []]
d_low   = [[], [] ,[], []]
d_close = [[], [] ,[], []]

dates = { '11-09-2018': 0, '09-10-2018': 1, '09-11-2018': 2, '11-12-2018': 3 }
inv_dates = {v: k for k, v in dates.items()}
print(dates.keys())

for raw in data_read:
    id = dates[raw[2]]
    d_open[id].append(float(raw[4]))
    d_high[id].append(float(raw[5]))
    d_low[id].append(float(raw[6]))
    d_close[id].append(float(raw[7]))

figure = go.Figure()
for i in range(4):
    cur_date = inv_dates[i]
    n = cur_date + ' - Open'
    figure.add_trace(go.Box(y=pd.DataFrame(d_open[i], columns=[n])[n], name=n))

    n = cur_date + ' - High'
    figure.add_trace(go.Box(y=pd.DataFrame(d_high[i], columns=[n])[n], name=n))

    n = cur_date + ' - Low'
    figure.add_trace(go.Box(y=pd.DataFrame(d_low[i], columns=[n])[n], name=n))

    n = cur_date + ' - Close'
    figure.add_trace(go.Box(y=pd.DataFrame(d_close[i], columns=[n])[n], name=n))

figure.update_layout(legend=dict(yanchor="top", orientation="h", y=1.2))
figure.update_xaxes(tickangle=90, title_standoff=25)
figure.show()