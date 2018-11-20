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
public class ToggleGatherer extends Command {
  boolean gather_state = false;
	
    public ToggleGatherer() {
      requires(Robot.gather);
      this.gather_state = !Robot.gather.state;
    }

    public ToggleGatherer(boolean state) {
      requires(Robot.gather);
      this.gather_state = state;
    }

    protected void initialize() {
    }

    protected void execute() {
      if (this.gather_state == false) {
        // turn gather off
        Robot.gather.stop();
        Robot.gather.state = false;
      }
      else {
        // turn gather on
        Robot.gather.setGatherSpeed(RobotMap.Values.gatherSpeed);
        Robot.gather.state = true;
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

