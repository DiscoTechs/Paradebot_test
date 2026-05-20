// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Horn;


import java.lang.ModuleLayer.Controller;

import com.ctre.phoenix.ButtonMonitor;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;


/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX m_leftMotor = new WPI_TalonSRX(12);
  private final WPI_TalonSRX m_leftMotorFollow = new WPI_TalonSRX(4);
  private final WPI_TalonSRX m_rightMotor = new WPI_TalonSRX(19);
  private final WPI_TalonSRX m_rightMotorFollow = new WPI_TalonSRX(11);
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);
  private final XboxController m_stick = new XboxController(0);
  boolean leftTrigger = m_stick.getLeftBumperButtonPressed();

  private final Horn robotHorn = new Horn();
  

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightMotorFollow);
    SendableRegistry.addChild(m_robotDrive, m_leftMotorFollow);




    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
     
    m_robotDrive.arcadeDrive(-m_stick.getLeftY(), -m_stick.getLeftX());
    // System.out.println("controler work");

   if (m_stick.getAButton() == true) { 
     robotHorn.horn(0.25);
     System.out.println("horn");
    }else if (m_stick.getBButton() == true) { 

       robotHorn.horn(0.5);
    } else if (m_stick.getXButton() == true){

      robotHorn.horn(0.75);
    } else if (m_stick.getYButton() == true){
      robotHorn.horn(1.0);
    } else{
      robotHorn.horn(0.0);
    }
  }


}



    
 
  


