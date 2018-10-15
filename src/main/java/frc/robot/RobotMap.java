/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Ports {
		public static int
		//CAN
		leftTalonPort = 3,
		rightTalonPort = 8,
		
		//PWM
		leftVictorPort = 4,
		leftVictorPort2 = 5,
		rightVictorPort = 6,
		rightVictorPort2 = 7,
		
		//PNEUMATICS
		shifterSolenoidLow = 0,
		shifterSolenoidHigh = 1,

		//GAMEPADS
		GamePad1 = 0, 
		GamePad2 = 1;
		
		//USB
		public static final SerialPort.Port AHRS = SerialPort.Port.kUSB;
		
	}
	
	public static class PDPPorts {
		public static int
		
		rightDriveTrain = 0,
		rightDriveTrain2 = 1,
		leftDriveTrain = 12,
		leftDriveTrain2 = 13,
		leftDriveTrainTalon = 14,
		rightDriveTrainTalon = 15;
	}
	
	public static class Values {
		
		public static double
		inchesPerTick = (3.954*Math.PI)/4096,	//inches per encoder tick
		ticksPerFoot = ((49152/(3.97*Math.PI)))*0.9, //3940, //encoder ticks per foot	
		
		robotLength = 33.25, //in inches
		robotWidth = 37.25,
		robotWheelBase = 30,
		
		driveDistanceP = 0.00025,
		driveDistanceI = 0.0,
		driveDistanceD = 0.0,
		driveAngleP = 0.015, //0.035, then 0.0138, then 0.0244 (important one), then 0.00694, then 0.0138
		driveAngleI = 0.002595, //0.000138 (important one) then 0.000038 then 0.000138 then 0.0001037 
		//then 0.000138 then 0.000514
		driveAngleD = 0,
		
		//voltage limits in amps
		drivetrainLeftLimit = 81, //81
		drivetrainRightLimit = 81; //81
	}

	public static class Buttons {
		public static int
		
		shiftButton = 5;
	}
}
