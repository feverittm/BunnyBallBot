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
public class ToggleSorter extends Command {
  boolean state = false;
  boolean transport_mode = false;  // just transport all balls to output/rear hopper
	
    public ToggleSorter() {
      requires(Robot.sorter);
      this.state = !Robot.gather.state;
      this.transport_mode = false;
    }

    public ToggleSorter(boolean state) {
      requires(Robot.sorter);
      this.state = state;
      this.transport_mode = false;
    }

    public ToggleSorter(boolean state, boolean transport) {
      requires(Robot.sorter);
      this.state = state;
      this.transport_mode = transport;
    }

    protected void initialize() {
    }

    protected void execute() {
      if (this.state == false) {
        // turn sorter off
        Robot.sorter.stop();
        Robot.sorter.state = false;
      }
      else {
        // turn gather on
        if (transport_mode == true) {
          // no sorting
          Robot.sorter.setSortSpeed(RobotMap.Values.sorterSpeed);
        } else {
          // full ball sort
          System.out.println("Color Sorting not ready yet");
        }
        Robot.sorter.state = true;
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

