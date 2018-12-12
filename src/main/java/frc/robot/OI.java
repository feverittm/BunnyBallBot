/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDriveForward;
import frc.robot.commands.ShiftCommand;
import frc.robot.commands.ToggleBallMode;
import frc.robot.commands.ToggleFrontHopper;
import frc.robot.commands.ToggleGatherer;
import frc.robot.commands.ToggleRearHopper;
import frc.robot.commands.ToggleSorter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick 
	GamePad1, 
  	GamePad2;
  
  public final JoystickButton
	//decellToggleButton,
	shiftButton,
	ballModeButton;

	public OI() {
		//JOYSTICK INIT
		GamePad1 = new Joystick(RobotMap.Ports.GamePad1);
    	GamePad2 = new Joystick(RobotMap.Ports.GamePad2);
    
    //DRIVETRAIN BUTTONS
		shiftButton = new JoystickButton(GamePad1, RobotMap.Buttons.shiftButton);
		shiftButton.whenPressed(new ShiftCommand());

	// Control Buttons
		ballModeButton = new JoystickButton(GamePad1, RobotMap.Buttons.ballmodeButton);
		ballModeButton.whenPressed(new ToggleBallMode());

	//SmartDashboard Buttons
		SmartDashboard.putData("Toggle Gather", new ToggleGatherer());
		SmartDashboard.putData("Toggle Front Hopper", new ToggleFrontHopper());
		SmartDashboard.putData("Toggle Sorter", new ToggleSorter());
		SmartDashboard.putData("Toggle Rear Hopper", new ToggleRearHopper());
		SmartDashboard.putData("Drive Forward by 500 Tics", new AutoDriveForward(500));
  }

	public double getLeftY() {
		return joystickDeadband(-GamePad1.getRawAxis(1));
	}
	
	public double getRightY() {
		return joystickDeadband(-GamePad1.getRawAxis(5));
	}
	
	public double getRightX() {
		return joystickDeadband(GamePad1.getRawAxis(4));
	}
	
	public static double joystickDeadband(double x) {
		if(Math.abs(x) < 0.05) {
			return 0;
		} else {
			return x;
		}
	}
	
}

