# DSAVisualiser

This is a project I worked on between March and September of 2022.  
The idea came from my university course having a Data Structures and Algorithms module starting in September of 2022.
I wanted to create a useful visual project to help others on my course and deepen my own knowledge of the area.
My self-imposed deadline was 'before the DSA module starts', and I'm proud to say I have succeded.
I first created a Minimum Viable Product: visualisers for Stacks and Queues with a menu for navigation, and have built off of it.

The releases section contains the full version, but the `download` folder also contains work-in-progress builds.
I've realeased it as `.jar`, so it should work with any desktop OS that runs Java, but I have encountered a compatability issue that I'm trying to resolve.
I will update this section as I find out more.

I selected 6 Data Structures, and 6 Algorithms that I thought visualisation would be useful in the learning of.  
I didn't include Hash Maps, for example, because I feel that once you understand Hashing, a tool to show the compound with a basic Array isn't especially useful.

One of my ideas for the project was to have comprehensive implementations of all the Data Structures and Algorithms that would be used by the visualisation tool.
This way, once you've seen them in action, you can look at the source code and see how they were implemented.  
Unfortunately, I discovered that this wasn't as practical as I'd hoped.
With a Stack, for example, you are only able to view the top item.
For the visualisation to be more effective, I needed to draw all of the values. This means that the implementation of the Stack visual doesn't contain a '*true*' stack implementation. 
Further, I had to step through the Algorithms incrementally in order to show what was happening.
Java does not have built-in compatability for coroutines/generators, so the implementations loose their beginner-friendliness.

The project is built using Java and the <a href="https://github.com/libgdx/libgdx">LibGDX</a> framework.

For those who are interested, there are apporximately 5500 lines of code.

![image](https://user-images.githubusercontent.com/97246704/192095688-b48f1faa-4f02-4ca6-9f1d-4628f7d0924a.png)
![image](https://user-images.githubusercontent.com/97246704/192096764-a850e61b-7dcc-4917-be43-253f38340592.png)
![image](https://user-images.githubusercontent.com/97246704/192096212-8b13abe0-6608-40fb-97da-e6c2bae8e92a.png)


Thanks to <a href="https://www.dafont.com/mrmanet.d5509">Riciery Leal</a> for the <a href="https://www.dafont.com/vcr-osd-mono.font">VCR OSD Mono font</a>.  
Thanks to <a href="https://github.com/TheWelshEngineer">Kathryn / TheWelshEngineer</a> for 6 of the preset themes, including the 3 colourblind options.

