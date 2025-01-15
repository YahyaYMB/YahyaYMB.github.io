---
title: Handheld Game Console
description: Final project for TEJ3MR course
---

1. TOC
{:toc}

## The Idea
![Console Picture](./Assets/handhelds.jpg)

The main idea of this project is to create an unique handheld device using a Raspberry Pi; which is capable of running all sorts of different games and software. The versatility and power that the Raspberry Pi brings made it the ideal board to base the project off of.

Additionally, I wanted to make something that used all of the skills that were built in the TEJ3MR course including: soldering, 3D modeling and circuits. Creating a console creates a difficult yet entertaining project that will further strengthen my skills in engineering.

## Materials
- Raspbery Pi 4B w/ SD card & Heatsink
- Arduino Pro Micro
- Screen (DSI compatible)
- Pi-Sugar Battery Module + 40 Pin GPIO header
- Case/Housing
- Push Buttons
- Thumbsticks
- PerfBoards

## Controls
One of the core aspects of gaming is being able to control movement in an easy and effective way. In my handheld gaming console, I used a microcontroller (Arduino Pro Micro) to control the gamepad.

Esentially, all the controls are wired to the Arduino. This means that each time the controls are interacted with, it goes through the Arduino first, then the Raspberry Pi. Although this may produce some input lag, this is easily negated by implementing a wired connection between the Raspberry Pi and  Arduino through a simple Micro-USB to USB-A cable.

## Screen & Power
Another core aspect of gaming is being able to play your games comfortably. This is why I decided to make two configurations of the handheld; Portable and Non-Portable.

### Portable
In the portable configuration, the console relies on an in-built screen. To allow for it to be completly portable, the handheld will also have an internal battery which can be charged, allowing the user to play almost anywhere. 

Additionally, this configuration will also be capable of outputting video to an external display. Moreover, the console will be able to function when the internal battery is depleted with the help of an external power source (i.e. power bank, outlet, etc.).

### Non-Portable
Unlike the portable configuration, the non-portable setup will not have a built-in display or battery. This is because this configuration is intended to be plugged into a tv/monitor and power source, mostly to play at home. 

Additionally, this configuration will rely on the Raspberry Pi's in-built bluetooth to allow for pairing with external gamepads and allow multiple people to play together. Alternatively, the option to use the built-in controls will always be available.

## Console Housing
For our design, we initally wanted to design a acryllic case to showcase the internal components to the user to give it a futuristic look. However, we realized that we could not obtain acryllic so we improvised and just made brackets to ensure functionality. These brackets will also allow us to further develop our console in the future to possibly add acryllics.

## Final Assembly
kashdfkjahskdjfhakjshdf

## Conclusion
kjhasdkjfjkashfkjhasjkdf

[Previous Page](./)
