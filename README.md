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

Pressing Button 1 on the left joystick toggles the drivetrain between high gear and low gear.

### Gamepad Controls
Control               | Button
----------------------|--------
Intake                | Left Joystick Y-Axis Up
Outtake               | Left Joystick Y-Axis Down
Raise Intake Elevator | 6
Lower Intake Elevator | 8
Actuate Intake        | 3

## Autonomous
Code for the autonomous period is handled in the [Auton](src/org/usfirst/frc/team3473/robot/commands/Auton.java) class. The autonomous code requires encoders and a gyro.

To configure the starting position of the robot on the field, position the slider (z-axis) on the left joystick before the match starts.

Slider Position | Robot Position
----------------|----------------
Top             | Left
Middle          | Center
Bottom          | Right

The autonomous code uses the [plate assignment data](https://wpilib.screenstepslive.com/s/4485/m/getting_started/l/826278-2018-game-data-details) given by the Field Management System at the beginning of the match.
