# Robot2018
Code for Team Sprocket's 2018 robot for FIRST Power Up.

## Components
The robot has the following components:
* A west coast drive that is able to shift gears (using a DoubleSolenoid)
* An intake and an elevator that can lift power cubes to the scale
* A climb elevator, allowing the robot to climb at the end of the match

See the [RobotMap](src/org/usfirst/frc/team3473/robot/RobotMap.java) class for CANTalon IDs, pneumatics, etc.

## Controls
The robot is controlled by:
* A left joystick (port 0)
* A right joystick (port 1)
* A gamepad (port 2)

### Driving
This code uses an arcade drive; the y-axis of the left joystick controls the robot's forward/backward speed, while the x-axis of the right joystick controls the robot's turning.

Pressing Button 1 on the left joystick toggles the drivetrain from high gear to low gear.

### Gamepad Controls
Control               | Button
----------------------|--------
Intake                | 1
Outtake               | 2
Raise Intake Elevator | 6
Lower Intake Elevator | 8
Raise Climb Elevator  | 5
Lower Climb Elevator  | 7
