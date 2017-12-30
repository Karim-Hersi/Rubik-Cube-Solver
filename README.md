# Part 1 - How to use the program


To use the solver, input the state of your cube using the 2-D representation below. Click the color palette of the desired color and then click the square to update its color.

   ![image](https://user-images.githubusercontent.com/14824605/34399937-0d8f5c1e-eb5a-11e7-844c-cf24ab007ddd.png) 

You can change the dimensions for the solver by clicking the ‘menu’ tab in the top left color and then clicking ‘settings’. 

![image](https://user-images.githubusercontent.com/14824605/34400014-f2b650f4-eb5a-11e7-94a7-4eea39808bf8.png)


![image](https://user-images.githubusercontent.com/14824605/34400023-1b254ea0-eb5b-11e7-828f-67c5eb3cc008.png)

Once you change the dimensions, go back to the previous menu. You will notice that the user interface has been updated. Below show screen shots for the different dimensions of the Rubik Cube Solver. Note: Although the code is generalized to solve for different dimensions, it will take more CPU resources (time, memory) for larger dimensions.


![image](https://user-images.githubusercontent.com/14824605/34399839-4fb25dae-eb59-11e7-9096-b832fad9fcbe.png)

![image](https://user-images.githubusercontent.com/14824605/34399844-5a1bbd4e-eb59-11e7-87b1-5d25a827f9be.png)

![image](https://user-images.githubusercontent.com/14824605/34399865-8c035baa-eb59-11e7-9d16-584f4cca3497.png)


Under the color palette in the bottom right of the screen, you will notice two icons. The icon to the left is used to shuffle the cube. Clicking this button will provide a random solvable orientation on to the user interface.

![image](https://user-images.githubusercontent.com/14824605/34399888-b1778c62-eb59-11e7-80b2-f15a47b3865f.png)

![image](https://user-images.githubusercontent.com/14824605/34399899-c176040e-eb59-11e7-92f1-9aab4cc6248e.png)

Also, you can click the ‘reset’ icon under the color palette and to the right. This will reset the state of the cube to a solved state.

![image](https://user-images.githubusercontent.com/14824605/34399919-e16ff62a-eb59-11e7-86c8-c5906c1a5fce.png)

Once you have set up the state of your cube on to the user interface, click the solve puzzle icon. If the input state is a solvable state, the program will get the steps to solve this cube and walk the user to the solution. 

![image](https://user-images.githubusercontent.com/14824605/34400115-2dfe5584-eb5c-11e7-995b-72b821d07c7b.png)

![image](https://user-images.githubusercontent.com/14824605/34400130-499eb0d6-eb5c-11e7-813b-9faa8dc6ac65.png)



# Part 2 - Design Decisions

The first decision was to find a notation to uniquely specify each rotation on the cube. A common notation is used is to use a letter for the face that is being rotated and add a prime symbol (') if the rotation is counter clockwise. For example, "F" would specify to rotate the front face of the cube clockwise and "f'" would specify to rotate the front face of the cube counter clockwise. There is a default position in the cube where the green face is faced such that it is the front face. You can read about this notation in the link below.

https://ruwix.com/the-rubiks-cube/notation/

For this application, I wanted to provide a more general solution. I wanted to be able to run my code on different dimensions (2 by 2, 3 by 3, 4 by 4, 5 by 5) for experimental purposes. The notation described above is only effective for a 3 by 3 cube. For larger sixes (for example a 5 by 5 cube) you would not be able to rotate any of the middle layers. This would result in some states being unsolvable and most states not being optimal with the notation above. 

The notation I used to specify each move was by specifying the axis the layer rotated about, which layer was rotated, and the direction that of the rotation. 

![image](https://user-images.githubusercontent.com/14824605/34450879-8114f8d4-ece4-11e7-9350-8d1f18e8591c.png)

Below displays all the possible rotations about the Y - Axis.

![image](https://user-images.githubusercontent.com/14824605/34450906-c1c6adae-ece5-11e7-8b08-ad52a32744e6.png)

In an N x N cube, there are ( N cubic squares per column/row ) X ( 3 Axis to rotate about ) X ( 2 Directions for Clockwise and Counter Clockwise ) = 6N total possible rotations.

