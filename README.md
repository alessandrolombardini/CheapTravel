# CheapTravel

_CheapTravel_ is an application I developed to find possible trips that I could have made with FlixBus starting from the two stations closest to my home, Cesena and Rimini.

If you use the FlixBus site, you can find trips in just one way: you select two cities and the date, and the site give you all the trips available on that day. The only other thing you can do is checking all the route started from a city, that is a very usefull feature.

![Interface](https://github.com/alessandrolombardini/CheapTravel/blob/main/img/Flixbus.PNG)

This is enought if you need to travel from city to another one, but is not enought if you want to be inspired. In this flixbus doesn't work very well: it doesn't offer any kind of feature to find interesting trip you can do, for example, just to visit a city.

With _CheapTravel_ a feature of this kind is offer: you select the city you want to start from and the date. Stop. The system will give all the buses that start from that city that day. For each of them it offer the price, the departure time and the journey time.

How do I interact with this application? Via Telegram.

I use this application to be inspaired: when I had a free day I checked the bot where I could go from the station nearest my home. Below is showed an example:

![Interface](https://github.com/alessandrolombardini/CheapTravel/blob/main/img/Telegram.jfif)

## How I did

I extracted the list of cities and connection by the page of map, and I use it to do some scraping of the pages. The application do HTTP Request and then analyses the responses.
