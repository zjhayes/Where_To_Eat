# Where_To_Eat

## The Problem

Have you ever struggled to decide where to eat? We’ve all been there. You’re with a group of
indecisive friends, and no one is willing to suggest a place to eat. What if a program could decide for
you? And, if you can agree on a few preferences such as the style of food or whether you want take out,
the program does an even better job picking a place you’d all enjoy.

## The Solution

Yelp! provides free datasets that I’d like to use to create this program. The program will use a
Queue to store restaurant objects, it may be array based because adding and removing won’t be
necessary. Another Queue will store the results; I want to keep the first Queue in memory in case the
user changes their preferences. I’m using a HashMap to store State abbreviations along with their full
name. Sorting will not be necessary on the Restaurant Queue, but may be used on the list of States to
alphabetize them after a list of States available in the dataset is created.

The results will, at minimum, be based on the restaurant’s reviews and style of food, and
whether the restaurant is currently open. Ideally, the user(s) will provide a general idea of what they are
(or are not) in the mood for and the program will search for the best results. Users with fewer
preferences can expect more random results.

## Sprints

November 2nd: Have basic program design laid out. Create the classes and know how they
interact.

November 16th: Data structure created and capable of retrieving data from Yelp! data set.

November 30th: Basic functionality, program can return a list of local restaurants based on food
category and whether the restaurant is open.

December 7th: Finished product, add more features such as choosing between dine-in and takeout.
Clean up the code and ensure code has full test coverage.

https://www.yelp.com/dataset/documentation/main
