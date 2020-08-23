Project : Upstox coding assignment
        Create an Analytical Server "OHLC" (Open/High/Low/Close) time series based on the 'Trades'

Libraries used:
        spring-boot 2.3.3.RELEASE
        jackson-databind 2.11.1
        junit

Note: No comments added in Class or methods as they are designed for single responsibility and self explanatory functionality

Features:
        1) REST Controller to subscribe client

            1) POST http://localhost:7070/subscribe
               Body -> {"event": "subscribe", "symbol": "XETHZUSD", "interval": 15}

            2) GET http://localhost:7070/subscribers

        2) Read the historical trade data json file and ingest in internal Map data structure.
            map Key => Trade symbol
            map value => List of trade data for trade symbol

        3) OHLC compute worker creates bar chart data at 15 second interval for subscribed client based on trade timestamp data.
           It will publish bar chart on console

How to run in development:
        1) It is a spring boot maven project
        2) Main application.class is in com.upstox.test.config package
        3) Historical trade data (trades.json) file is main/resources folder, replace it with the data file you want to test
           and make sure file name is correct(trades.json) in main/resources folder
        4) Postman scripts are present in project root folder - > upstoxTest.postman_collection.json
        5) Start Application.class from com.upstox.test.config package
           Spring boot application will start at http://localhost:7070/
        5) Download postman scripts and execute POST method to subscribe client
           POST http://localhost:7070/subscribe -> {"event": "subscribe", "symbol": "XETHZUSD", "interval": 15}
        6) Application(trade data worker) will start ingesting historical trade data into internal map data structure
               {"sym":"XZECXXBT", "T":"Trade",  "P":0.01947, "Q":0.1, "TS":1538409720.3813, "side": "s", "TS2":1538409725339216503}
               sym : Stock name string
               T: Ignore this field string
               P: Price of Trade double
               Q: Quantity Traded double
               TS: Ignore this field uint64
               Side: Ignore this field string
               TS2: Timestamp in UTC uint64

        7) OHLC worker service will start publishing bar chart in 15 seconds internal on console
           **We can create web socket to publish real time bar chart
        8) Output data will be printed on console in below format

2020-08-23 06:28:41.647  INFO 17308 --- [pool-1-thread-1] com.upstox.test.ohlc.OhlcWorkerThread    : OHLC compute worker thread started at an interval of 15 seconds
{"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":227.98,"l":227.98,"c":0.0,"volume":0.02,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":227.98,"c":0.0,"volume":0.184,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.83,"c":0.0,"volume":0.6839999999999999,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.57,"c":0.0,"volume":0.7939999999999999,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.55,"c":0.0,"volume":5.794,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.55,"c":0.0,"volume":10.794,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.53,"c":0.0,"volume":10.994,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.52,"c":0.0,"volume":15.994,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.52,"c":0.0,"volume":20.994,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.47,"c":0.0,"volume":23.994,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.46,"c":0.0,"volume":24.018,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.3,"c":0.0,"volume":50.794,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.3,"c":0.0,"volume":58.794,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.3,"c":0.0,"volume":94.018,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}
{"o":227.98,"h":228.28,"l":226.26,"c":226.26,"volume":100.794,"event":"ohlc_notify","symbol":"XETHZUSD","bar_num":43}

