/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *  Toggle the gatherer on/off
 */
public class ToggleFrontHopper extends Command {
  boolean state = false;
	
    public ToggleFrontHopper() {
      requires(Robot.frontHopper);
      this.state = !Robot.frontHopper.state;
    }

    public ToggleFrontHopper(boolean state) {
      requires(Robot.frontHopper);
      this.state = state;
    }

    protected void initialize() {
    }

    protected void execute() {
      if (this.state == false) {
        // turn gather off
        Robot.frontHopper.stop();
        Robot.frontHopper.state = false;
      }
      else {
        // turn gather on
        Robot.frontHopper.setHopperSpeed(RobotMap.Values.frontHopper_HSpeed, RobotMap.Values.frontHopper_VSpeed);
        Robot.frontHopper.state = true;
      }
    }

    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}

