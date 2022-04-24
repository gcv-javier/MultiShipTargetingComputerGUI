# MultiShip Targeting Computer GUI

An application for the Uboat sim that helps you to get the timing between firing torpedoes
to several ships and impacting at the same time.

![](https://i.im.ge/2022/04/23/lHUo4a.png)

### :fire: Features

- _`Up to 5 ships.`_
- _`Same metrics as in the Uboat game.`_
- _`Torpedo range automatically set based on distance to target.`_
- _`Live countdowns to make the shooting more accurate.`_
- _`Different calculation methods.`_


## Introduction

This is a Java application, with a *GUI*, based on the idea of [-PringlesMan-](https://www.reddit.com/user/-PringlesMan-/).

I was playing the Uboat sim and I was getting problems hitting ships at the same time,
so the alarm sounded in the convoy so the ships started to change their course, 
making impossible reaching them again.
Then I found this [reddit](https://www.reddit.com/r/uboatgame/comments/tukmrx/i_present_to_you_my_multi_ship_targeting_computer/),
where -PringlesMan- had made a console based Java application to calculate the times between shots.
The app didn't have a GUI and it was based on the current distance to the ship, not in the
real distance where the torpedo will collide with the ship.

I started doing some research where I discovered how the uboats calculated the [angle of the torpedo](http://www.tvre.org/en/torpedo-calculator-t-vh-re-s3),
using the law of the sines.
Also found [this](https://www.reddit.com/r/HomeworkHelp/comments/ovtsvq/grade_10the_torpedo_is_fired_from_point_a_at_the/)
where I was able to calculate the torpedo angle equation:

![](https://i.im.ge/2022/04/23/lHUMTL.png)

Later on I found the same equation [here](http://www.simhq.com/_naval/PDF/naval_009print.pdf)

![](https://i.im.ge/2022/04/23/lHLGRc.png)

Then to calculate the time of impact I needed the distance to impact, where the 
law of the sines was useful again:

Finally, all the calculations I did were explained [here](https://drive.google.com/file/d/1H_RYhZf-xX3YBnIS-3oeUDl9bj7PxiE_/view)

## Run

As Java is multiplatform, `mstc` can be executed in all the platforms. 
Although as the Uboat sim works in Windows then a Windows executable file is created
to facilitate the usability without the need of having Java installed.

### Downloads:

Available versions:

| Platform | Type   | Version | Link                                                                                                                                |
|----------|--------|---------|-------------------------------------------------------------------------------------------------------------------------------------|
| Windows  | alpha  | 0.1.0   | [Download](https://drive.google.com/file/d/1RAPuY5T-ybbfisxyqmVhjIHc9FEYgJtj/view?usp=sharing) |
| Java     | alpha  | 0.1.0   | [Download](https://drive.google.com/file/d/1g1Y3wmdCHD_9lYRTakhQSnBflQkK-KG7/view?usp=sharing) |
| Windows  | alpha  | 0.1.1   |          |
| Java     | alpha  | 0.1.1   |          |
| Windows  | beta   | 0.2.0   |                       |
| Java     | beta   | 0.2.0   |                       |

(we'll prepare a release in Github and link here the .zip/.jar files of the executables plus the source zip file)

#### Run the .exe file:

Unzip the file and execute the `.exe` file.

#### Run the jar:

Running the fact jar requires at least `Java 17`.

```
java -jar .\mstc-0.1.0-shaded.jar
```

## For Developers

### Build:

Building this project requires `Java 17` and `Maven`.

```
mvn clean package
```

### Dependencies

Maven will download all the necessary dependencies.
To generate the .exe file `launch4j` is used.

## How to use it

Check out these videos to know how the app works: https://youtu.be/4YCrCG-9Y0w, https://youtu.be/RYJ8tGdLNBE

In here there is a test of the app with the game: https://youtu.be/RQey3hF7SVE

I'll keep uploading more videos to the channel with more tests.

### Steps

- you can set the Uboat game to Windowed to avoid Uboat to minimize when `mstc` is opened.
- being in the middle of a hunt, recognise the ships, gather the necessary information (speed, distance, aob),
- pause the game
- set the data in the mstc app (speed, aob, distance, the torpedo speed),
- in the mstc app: press the `Calculate` button to calculate the torpedo angle, distance and time of impact,
- (optional) then with the game still paused, draw in the map the lines of the triangle, with the calculated torpedo angle and see if the trajectory of the ship and the torpedo will collide in the estimated distance. (just to know in advance if the calculation was right)
- unpause the game and flood the first torpedo(es)
- pause it again when the torpedo is just flooded
- press the Uboat fire button in the mstc app.
- quickly go back to the game, unpause it, and fire the torpedo when the clip audio ends.
- in the meantime, flood the torpedo(es) for the next target and wait for the countdown clip and the `FIRE!!` message to be shown to fire the torpedo in the game.
- (do the same last step again depending on the targets number)

To calculate the necessary information I recommend you the [Lite_ly Salted](https://www.youtube.com/channel/UCu39K2RogR-Vgxberp1-hVw) channel, 
he explains everything that seems super easy.

How to calculate Angle of Bow: https://www.youtube.com/watch?v=kqVO-ESAm8s

The optional part of drawing the triangles would look like this:
![](https://i.im.ge/2022/04/22/lz4gVq.png)
![](https://i.im.ge/2022/04/22/lz4W0P.png)
![](https://i.im.ge/2022/04/22/lz4kEr.png)

## For Testers

### To be tested

Some features are still on testing. 

- Calculating the time of impact with a unit that is not metric (km, km/h).
- With more than 3 targets.

### Bugs not fixed

I still need to review some known bugs:

- When at least two targets get the same time of impact, they are treated as unique. I think this is not a normal thing to happen in the game, but I'll need to review it.
- The Uboat fire button moves depending on the length of the ship names making that the user might need to expand the size of the window.


## New possible features

We are opened to suggestions to improve the app.
PringlesMan got a couple of interesting ideas like using time compression and using the voice recognition to launch the torpedoes.

- Implementing, if possible, the [parallax correction](http://www.tvre.org/en/torpedo-calculator-t-vh-re-s3) for a more accurate timing. Because the current Trigonometric calculation doesn't consider the gyro angle of the torpedo.
- Using the same time compression as in the game, to avoid waiting long for the torpedoes to impact. The synchronisation here needs to be almost perfect.
- Reproduce some clips to give orders to the game avoiding pressing the Fire button, also if possible to flood torpedoes.
- Export the app to an Uboat mod. This won't be easy, but I suppose the mod could read the calculated data (speed, distance, AOB) making quicker the whole process.


## Acknowledgment
- PringlesMan: for your great idea, for your entrepreneurship to learn a language to solve a problem you got, 
for your support and constant ideas of improvement.
- Nitish Borade: for your help with converting a jar to a Windows executable.
- Andrew Misseldine: for your help with the Maths.
- Reddit's users: always supporting and helping with the project.


## Donate

MSTC is free and without any ads. If you appreciate that, please consider donating to the Developer.

[![Donate](https://i.im.ge/2022/04/23/lHVyL0.png)](https://www.paypal.com/donate/?business=L9D87NJMZSGUJ&no_recurring=0&item_name=To+keep+the+boat+floating.&currency_code=EUR)

License
=======
    Copyright 2022 Javier García-Cuerva Velasco

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.