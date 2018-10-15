/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ShiftCommand;

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
	shiftButton;

	public OI() {
		//JOYSTICK INIT
		GamePad1 = new Joystick(RobotMap.Ports.GamePad1);
    GamePad2 = new Joystick(RobotMap.Ports.GamePad2);
    
    //DRIVETRAIN BUTTONS
		shiftButton = new JoystickButton(GamePad1, RobotMap.Buttons.shiftButton);
		shiftButton.whenPressed(new ShiftCommand());
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

