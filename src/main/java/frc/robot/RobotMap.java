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
 * 
 * Type	Motor Controller	PDP Port	Breaker	CAN ID/PWM port	Purpose
 * CAN	Victor SPX			15			40		1				Drivetrain
 * CAN	Victor SPX			14			40		2				Drivetrain
 * CAN	Victor SPX			13			40		3				Drivetrain
 * CAN	Victor SPX			12			40		4				Drivetrain
 * CAN	Victor SPX			11			30		5				Front Hopper
 * CAN	Victor SPX			10			30		6				Front Hopper
 * CAN	Talon SRX			0			40		7				Intake
 * CAN	Talon SRX			1			40		8				Sorter
 * CAN	Talon SRX			2			40		9				Drivetrain
 * CAN	Talon SRX			3			40		10				Drivetrain
 * PWM	Victor SP			4			30		8				Rear Hopper
 * PWM	Victor SP			5			30		9				Output
 */

public class RobotMap {
	public static class Ports {
		public static int
		//CAN
		leftTalonPort = 9,
		leftVictorPort = 1,
		leftVictorPort2 = 2,
		rightTalonPort = 10,
		rightVictorPort = 3,
		rightVictorPort2 = 4,

		//Ball Gatherer/Collector/Intake
		gatherMotorPort = 8, // change due to broken TalonSRX on channel 7

		//Front Hopper
		frontHopperMotorPortH = 5,
		frontHopperMotorPortV = 6,
		
		// Sorter
		sortTalon = 7,

		// Rear Hopper (on PWM)
		rearHopperPWM = 8,

		// ball ejector/output (on PWM)
		ballOutputPWM = 9,

		//PNEUMATICS
		shifterSolenoidLow = 0,
		shifterSolenoidHigh = 1,
		sortSolenoid = 2,
		bunnyDumperSolenod = 4,

		//Digital Inputs
		ballSensor = 0,

		//GAMEPADS
		GamePad1 = 0, 
		GamePad2 = 1;
		
		//USB
		public static final SerialPort.Port AHRS = SerialPort.Port.kUSB;
		
	}
	
	public static class PDPPorts {
		public static int
		
		leftDriveTrainMaster = 2,
		leftDriveTrainS1 = 15,
		leftDriveTrainS2 = 14,
		rightDriveTrainMaster = 3,
		rightDriveTrainS1 = 13,
		rightDriveTrainS2 = 12,
		gatherMotorPort = 0,
		frontHopperMotorPortH = 11,
		frontHopperMotorPortV = 10,
		sorterMotorPort = 1,
		rearHopperMotorPort = 4,
		ballOutputMotorPort = 5;
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
		
		//current limits in amps
		drivetrainLeftLimit = 81, //81
		drivetrainRightLimit = 81, //81

		// Gatherer
		gatherSpeed = 0.8,

		// FrontHopper
		frontHopper_HSpeed = 0.5,
		frontHopper_VSpeed = 0.5,
		
		// RearHopper
		rearHopper_Speed = 0.5,

		//Sorter
		sorterSpeed = 0.25,
		ejectTime = 250,
		sorterCurrentLimit = 20,

		// dummy value to hold semi-colon at the end of the list :-)
		values_end;
	}

	public static class Buttons {
		public static int
		
		shiftButton = 5,
		ballmodeButton = 1;
	}
}
