# CheapTravel

_CheapTravel_ is an application I developed in 2017 to find possible trips that I could have made with FlixBus starting from the two stations closest to my home, Cesena and Rimini.

If you use the FlixBus’ site, you can find trips in just one way: you select two cities and the date, and the site shows you all the trips available with those constraints. The only other thing you can do is checking all the routes started from a city.

![Interface](https://github.com/alessandrolombardini/CheapTravel/blob/main/img/Flixbus.PNG)

These features are enough if you need to travel from one city to another one, but are not if you want to be inspired. On this Flixbus’ site doesn’t work very well: it doesn’t offer any kind of service to find exciting trips you can do.

For example: if a have a free day, where can I go? Well, Flixbus doesn’t help in this.

With _CheapTravel_ a service of this kind is offered: you select the city you want to start from and the date you want to leave, and the system will give you all the buses that start from that city that day. For each, it will be given to you the price, the departure time and the journey time. Very useful!

Below is shown an example:

![Interface](https://github.com/alessandrolombardini/CheapTravel/blob/main/img/Telegram.jfif)

As you can see by the image above, I used Telegram as interface. I used it for purposes like this one a lot of time, and I think is a really good solution is you don’t have a lot of resources and time available.

## How does it work?
Scraping is the answer.

Firstly I extracted the list of cities (codes and names) and their connection by the page of the map (the one showed above). All the codes were there, inside the HTML.

Secondly, when you select a city of departure, some HTTP requests are made. There is no need to do a POST, it’s enough a GET. All the needed data, like departure city, arrival city and date, can be easily put inside the URL. For each city connected to the departure city is made an HTTP Request, which allows me to get the buses that cover that route on a specific date.


Then every HTTP request is analyzed to extract from those all the data necessary, which are sent to the user via Telegram.

## Notes

This application is quite old, as I said before it was developed in 2017. The code, nowadays, doesn’t work anymore for different reasons.

First of all, I think the URL format of web pages is changed. Secondly, the code is not fully complete. I don’t find anymore the complete code because, at that time, I didn’t use GitHub. I also developed this application in Python, which allows me to create an easier code than this one, which is in Java. I lost also that.
