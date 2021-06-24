package frc.robot;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.MotorSafety;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.time.*;
import java.math.*;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANAnalog;
import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;
import com.revrobotics.CANAnalog.AnalogMode;

// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.PWMSparkMax;
// import edu.wpi.first.wpilibj.SpeedController;
// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
// import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
//import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

public class SwerveModule {
  private CANSparkMax m_driveMotor;
  private CANSparkMax m_turningMotor;

  private CANEncoder m_driveEncoder;
  private CANAnalog m_turningEncoder;
  private int turnID;

//  private final CANPIDController m_drivePIDController;
  private final CANPIDController m_turningPIDController;

  public double kP, kI, kD, kIz_1, kFF_1, kMaxOutput, kMinOutput;
  /**
   * Constructs a SwerveModule.
   *
   * @param driveMotorChannel ID for the drive motor.
   * @param turningMotorChannel ID for the turning motor.
   */
  public SwerveModule(int driveMotorChannel, int turningMotorChannel, double kP_in, double kI_in, double kD_in) {
    m_driveMotor = new CANSparkMax(driveMotorChannel, MotorType.kBrushless);
    m_turningMotor = new CANSparkMax(turningMotorChannel, MotorType.kBrushless);
    m_turningMotor.setInverted(true);

    m_turningEncoder = m_turningMotor.getAnalog(AnalogMode.kAbsolute);
    m_turningPIDController = m_turningMotor.getPIDController();
//    m_drivePIDController = m_driveMotor.getPIDController();
    m_turningPIDController.setFeedbackDevice(m_turningEncoder);
//    m_drivePIDController.setFeedbackDevice(m_driveEncoder);
  

    turnID = turningMotorChannel;

    kP = kP_in;
    kI = kI_in;
    kD = kD_in;
    kIz_1 = 0;
    kFF_1 = 0;

    kMaxOutput = 1;
    kMinOutput = -1;

    m_turningPIDController.setP(kP);
    m_turningPIDController.setI(kI);
    m_turningPIDController.setD(kD);
    m_turningPIDController.setIZone(kIz_1);
    m_turningPIDController.setFF(kFF_1);
    m_turningPIDController.setOutputRange(kMinOutput, kMaxOutput);

  }

  /**
   * Returns the current state of the module.
   *
   * @return The current state of the module.
   */
  public SwerveModuleState getState() {
    return new SwerveModuleState(m_driveEncoder.getVelocity(), new Rotation2d(m_turningEncoder.getPosition()));
  }

  /**
   * Sets the desired state for the module.
   *
   * @param desiredState Desired state with speed and angle.
   */
  public void setDesiredState(SwerveModuleState desiredState) {
    // Optimize the reference state to avoid spinning further than 90 degrees
    SwerveModuleState state =
        SwerveModuleState.optimize(desiredState, new Rotation2d(m_turningEncoder.getPosition()));


    // Calculate the drive output from the drive PID controller.
    final double driveOutput = state.speedMetersPerSecond;
    System.out.println("drive output: " + driveOutput);

    //final double driveFeedforward = m_driveFeedforward.calculate(state.speedMetersPerSecond);

    // Calculate the turning motor output from the turning PID controller.
    
    m_driveMotor.setVoltage(driveOutput);
    double angle = state.angle.getDegrees();

    long count = System.currentTimeMillis();

//    angle = (angle) * 3.35/360;
      angle = 5 * 3.35/360;
//if(angle <= 0){
     // angle = angle * -1 + 0.00000001;
    //}
    m_turningPIDController.setReference(angle, ControlType.kPosition);
    if(turnID == 8) {
      System.out.println("degrees: " + state.angle.getDegrees() + "\t output: " + angle + "\t encoder: " + m_turningEncoder.getPosition() + "\t count: " + count);
  }     // TODO: make 3rd and 4th quadrant work
}
  
public void setDriveSpeed(double xSpeed, double ySpeed){
    double magnitude = Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));

    m_driveMotor.set(magnitude);
}



}
