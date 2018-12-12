/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDoNothing;
import frc.robot.subsystems.BallOutput;
import frc.robot.subsystems.BunnyDumper;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FrontHopper;
import frc.robot.subsystems.Gatherer;
import frc.robot.subsystems.RearHopper;
import frc.robot.subsystems.Sorter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static BallOutput ballOutput;
  public static BunnyDumper bunnyDumper;
  public static DriveTrain drivetrain;
  public static FrontHopper frontHopper;
  public static Gatherer gather;
  public static RearHopper rearHopper;
  public static Sorter sorter;
  public static PowerDistributionPanel pdp;
  public static Compressor compressor;
  public static OI m_oi;
  public static Logger logger;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    try {
      drivetrain = new DriveTrain();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      frontHopper = new FrontHopper();
    } catch (Exception e) {
      e.printStackTrace();
    }
 

    try {
      gather = new Gatherer();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      bunnyDumper = new BunnyDumper();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      rearHopper = new RearHopper();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      ballOutput = new BallOutput();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      sorter = new Sorter();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Global subsystems
    try {
      compressor = new Compressor();
      compressor.clearAllPCMStickyFaults();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      pdp = new PowerDistributionPanel();
      pdp.clearStickyFaults();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      m_oi = new OI();
    } catch (Exception e) {
      e.printStackTrace();
    }

    logger = Logger.getInstance();
    
    LiveWindow.disableTelemetry(pdp); // turn-off the telemetry features in Livewindow to stop the CTRE Timeouts
  
    m_chooser.addDefault("Default Auto", new AutoDoNothing());
    // chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    logger.close();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    logger.openFile();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    updateAllSmartDashboard();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    logger.logAll();
    updateAllSmartDashboard();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    updateAllSmartDashboard();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateAllSmartDashboard();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private void updateAllSmartDashboard() {
    Robot.drivetrain.updateDashboard();
    Robot.sorter.updateDashboard();
  }
}
