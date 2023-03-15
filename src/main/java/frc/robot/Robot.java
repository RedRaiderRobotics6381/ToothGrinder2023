// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmSubsystem;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;

    double targetPos;

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        m_robotContainer = new RobotContainer();

 

    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and
     * test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
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
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        //temporary!!!!!!
        double rotateoffset = 2;
        double pos1 = 90;
        double pos2 = 110;
        double pos3 = 130;
        double pos4 = 190;
        double rotateSpeed = 0.15;

        System.out.println(ArmSubsystem.armRotateEncoder.getPosition());
       // rotate motor set position
    // ArmSubsystem.armRotateMotor.set(RobotContainer.secondaryJoystick.getRawAxis(5));
        // if (RobotContainer.secondaryJoystick.getRawButton(7)){
        //     if(ArmSubsystem.armRotateEncoder.getPosition() < positionGrab - rotateoffset){
        //         ArmSubsystem.armRotateMotor.set(rotateSpeed);
        //     }
        //     if(ArmSubsystem.armRotateEncoder.getPosition() > positionGrab + rotateoffset){
        //         // System.out.println("Woo");
        //         ArmSubsystem.armRotateMotor.set(rotateSpeed);
        //     }
        // }
        // if (RobotContainer.secondaryJoystick.getRawButton(8)){
        //     if(ArmSubsystem.armRotateEncoder.getPosition() < positionPlace - rotateoffset){
        //         ArmSubsystem.armRotateMotor.set(rotateSpeed);
        //     }
        //     if(ArmSubsystem.armRotateEncoder.getPosition() > positionPlace + rotateoffset){
        //         ArmSubsystem.armRotateMotor.set(-rotateSpeed);
        //     }
        // }
    //     ArmSubsystem.armRotateMotor.set(0);
    // if(RobotContainer.secondaryJoystick.getPOV() == 0 && ArmSubsystem.armRotateEncoder.getPosition() < 1.9){
    //     ArmSubsystem.armRotateMotor.set(-rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 0 && ArmSubsystem.armRotateEncoder.getPosition() < 1.9){
    //     ArmSubsystem.armRotateMotor.set(-rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 90 && ArmSubsystem.armRotateEncoder.getPosition() < 1.9){
    //     ArmSubsystem.armRotateMotor.set(-rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 90 && ArmSubsystem.armRotateEncoder.getPosition() < 1.9){
    //     ArmSubsystem.armRotateMotor.set(-rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 180 && ArmSubsystem.armRotateEncoder.getPosition() < 1.9){
    //     ArmSubsystem.armRotateMotor.set(-rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 180 && ArmSubsystem.armRotateEncoder.getPosition() < 1.9){
    //     ArmSubsystem.armRotateMotor.set(-rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 270 && ArmSubsystem.armRotateEncoder.getPosition() > 0.1){
    //     ArmSubsystem.armRotateMotor.set(rotateSpeed);
    // }
    // if(RobotContainer.secondaryJoystick.getPOV() == 270 && ArmSubsystem.armRotateEncoder.getPosition() > 0.1){
    //     ArmSubsystem.armRotateMotor.set(rotateSpeed);
    // }
       if(RobotContainer.secondaryJoystick.getPOV() == 0){
        targetPos = pos1;
       }
       if(RobotContainer.secondaryJoystick.getPOV() == 90){
        targetPos = pos2;
       }
       if(RobotContainer.secondaryJoystick.getPOV() == 180){
        targetPos = pos3;
       }
       if(RobotContainer.secondaryJoystick.getPOV() == 270){
        targetPos = pos4;
       }
       
       if(ArmSubsystem.armRotateEncoder.getPosition() > targetPos - rotateoffset){
        ArmSubsystem.armRotateMotor.set(-rotateSpeed);
       }
       if(ArmSubsystem.armRotateEncoder.getPosition() < targetPos + rotateoffset){
        ArmSubsystem.armRotateMotor.set(rotateSpeed);
       }

    // ArmSubsystem.armRotateMotor.set(RobotContainer.secondaryJoystick.getRawAxis(5)*0.2);
    
    // Color detectedColor = ArmSubsystem.m_colorSensor.getColor();
    // int proximity = ArmSubsystem.m_colorSensor.getProximity();
    

    // SmartDashboard.putNumber("Red", detectedColor.red);
    // SmartDashboard.putNumber("Green", detectedColor.green);
    // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putNumber("Proximity", proximity);

    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }
}
