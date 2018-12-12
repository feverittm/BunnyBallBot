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
public class ToggleRearHopper extends Command {
  boolean state = false;
	
    public ToggleRearHopper() {
      requires(Robot.rearHopper);
      this.state = !Robot.rearHopper.state;
    }

    public ToggleRearHopper(boolean state) {
      requires(Robot.rearHopper);
      this.state = state;
    }

    protected void initialize() {
    }

    protected void execute() {
      if (this.state == false) {
        // turn gather off
        Robot.rearHopper.stop();
        Robot.rearHopper.state = false;
      }
      else {
        // turn gather on
        Robot.rearHopper.setHopperSpeed(RobotMap.Values.rearHopper_Speed);
        Robot.rearHopper.state = true;
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

