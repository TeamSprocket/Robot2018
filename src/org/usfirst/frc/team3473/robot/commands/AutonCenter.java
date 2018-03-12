package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutonCenter extends Command{
	enum Stage {START, MOVE_FORWARD, TURN, ELEVATE, OUTTAKE};
	Stage current = Stage.START;
	
	protected void execute(){
		double speed = .5;
		double dist = RobotMap.leftEncoder.get();
		if(dist <= 700){
			Robot.drivetrain.setLeft(speed);
			Robot.drivetrain.setRight(speed);
		}
		else{
			RobotMap.leftEncoder.reset();
			RobotMap.rightEncoder.reset();
			RobotMap.gyro.reset();
			Robot.drivetrain.setLeft(0);
			Robot.drivetrain.setRight(0);
		}
	}
	
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
