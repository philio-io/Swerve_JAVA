// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends TimedRobot {
//  private final Joystick m_stick1 = new Joystick(1);
  private final Joystick m_stick2 = new Joystick(0);
  private final Drivetrain m_swerve = new Drivetrain();

  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
//  private final SlewRateLimiter m_xspeedLimiter = new SlewRateLimiter(3);
//  private final SlewRateLimiter m_yspeedLimiter = new SlewRateLimiter(3);
//  private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(3);

  @Override
  public void autonomousPeriodic() {
    driveWithJoystick(false);
    m_swerve.updateOdometry();
  }

  @Override
  public void teleopPeriodic() {
    driveWithJoystick(false);
  }

  private void driveWithJoystick(boolean fieldRelative) {
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    
    final var xSpeed = -m_stick2.getY() * Drivetrain.kMaxSpeed / 10;


    // Get the y speed or sideways/strafe speed. We are inverting this because
    // we want a positive value when we pull to the left. Xbox controllers
    // return positive values when you pull to the right by default.
    final var ySpeed = 0;//m_stick2.getX() * Drivetrain.kMaxSpeed / 10;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    final var rot = 0;
//        m_stick2.getY()
//            * Drivetrain.kMaxAngularSpeed / 3;
//    if(m_stick2.getY() <= 0) {
      m_swerve.drive(xSpeed, ySpeed, rot, fieldRelative);
//    }

  }
}




/////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////OLD CODE DOESNT WORK//////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.Joystick;
// //import edu.wpi.first.wpilibj.MotorSafety;
// import edu.wpi.first.wpilibj.PWMVictorSPX;
// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// import com.revrobotics.CANPIDController;
// import com.revrobotics.CANSparkMax;
// //import com.revrobotics.CANSparkMaxLowLevel;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import com.revrobotics.CANAnalog;
// import com.revrobotics.CANEncoder;
// import com.revrobotics.ControlType;
// import com.revrobotics.CANAnalog.AnalogMode;

// import edu.wpi.first.wpilibj.Encoder;

// /*
//  * The VM is configured to automatically run this class, and to call the functions corresponding to
//  * each mode, as described in the TimedRobot documentation. If you change the name of this class or
//  * the package after creating this project, you must also update the manifest file in the resource
//  * directory.
//  */
// public class Robot extends TimedRobot {
//   private final DifferentialDrive m_robotDrive =
//       new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
//   private final Joystick m_stick = new Joystick(0);
//   private final Timer m_timer = new Timer();
//   private CANSparkMax m_motor1;
//   private CANSparkMax m_motor2;
  
//   private CANPIDController m_pidController1;
//   private CANPIDController m_pidController2;

//   private CANEncoder m_encoder1;
//   private CANEncoder m_encoder2;
//   private CANAnalog m_analogSensor1;

//   public double kP_1, kI_1, kD_1, kIz_1, kFF_1, kP_2, kI_2, kD_2, kIz_2, kFF_2, kMaxOutput, kMinOutput;

//   //  private MotorSafety m_safety;

//   /**
//    * This function is run when the robot is first started up and should be used for any
//    * initialization code.
//    */
//   @Override
//   public void robotInit() {
//     // make the SparkMax
//     m_motor1 = new CANSparkMax(2, MotorType.kBrushless);
//     m_motor1.setInverted(true);

//     m_analogSensor1 = m_motor1.getAnalog(AnalogMode.kAbsolute);


//     m_pidController1 = m_motor1.getPIDController();
// //    m_pidController2 = m_motor2.getPIDController();

//     m_pidController1.setFeedbackDevice(m_analogSensor1);
// //    m_pidController2.setFeedbackDevice(m_encoder2);

//     kP_1 = 0.5;
//     kI_1 = 0;
//     kD_1 = 0.5;
//     kIz_1 = 0;
//     kFF_1 = 0.01;
// /*
//     kP_2 = 0.1;
//     kI_2 = 1e-4;
//     kD_2 = 1;
//     kIz_2 = 0;
//     kFF_2 = 0;
// */
//     kMaxOutput = 1;
//     kMinOutput = -1;

//     m_pidController1.setP(kP_1);
//     m_pidController1.setI(kI_1);
//     m_pidController1.setD(kD_1);
//     m_pidController1.setIZone(kIz_1);
//     m_pidController1.setFF(kFF_1);
//     m_pidController1.setOutputRange(kMinOutput, kMaxOutput);

// /*    m_pidController2.setP(kP_2);
//     m_pidController2.setI(kI_2);
//     m_pidController2.setD(kD_2);
//     m_pidController2.setIZone(kIz_2);
//     m_pidController2.setFF(kFF_2);
//     m_pidController2.setOutputRange(kMinOutput, kMaxOutput);
// */


//     SmartDashboard.putNumber("P Gain", kP_1);
//     SmartDashboard.putNumber("I Gain", kI_1);
//     SmartDashboard.putNumber("D Gain", kD_1);
//     SmartDashboard.putNumber("I Zone", kIz_1);
//     SmartDashboard.putNumber("Feed Forward", kFF_1);
//     SmartDashboard.putNumber("Max Output", kMaxOutput);
//     SmartDashboard.putNumber("Min Output", kMinOutput);
//     SmartDashboard.putNumber("Set Rotations", 0);
// //    SmartDashboard.putNumber("StickValue", 0);

//   }

//   /** This function is run once each time the robot enters autonomous mode. */
//   @Override
//   public void autonomousInit() {
//     m_timer.reset();
//     m_timer.start();
//   }

//   /** This function is called periodically during autonomous. */
//   @Override
//   public void autonomousPeriodic() {
//     // Drive for 2 seconds
//     if (m_timer.get() < 2.0) {
//       m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
//     } else {
//       m_robotDrive.stopMotor(); // stop robot
//     }
//   }

//   /** This function is called once each time the robot enters teleoperated mode. */
//   @Override
//   public void teleopInit() {}

//   /** This function is called periodically during teleoperated mode. */
//   @Override
//   public void teleopPeriodic() {

//     double p = SmartDashboard.getNumber("P Gain", 0);
//     double i = SmartDashboard.getNumber("I Gain", 0);
//     double d = SmartDashboard.getNumber("D Gain", 0);
//     double iz = SmartDashboard.getNumber("I Zone", 0);
//     double ff = SmartDashboard.getNumber("Feed Forward", 0);
//     double max = SmartDashboard.getNumber("Max Output", 0);
//     double min = SmartDashboard.getNumber("Min Output", 0);
//     double rotations = SmartDashboard.getNumber("Set Rotations", 0);

//     // if PID coefficients on SmartDashboard have changed, write new values to controller
//     if((p != kP_1)) { m_pidController1.setP(p); kP_1 = p; }
//     if((i != kI_1)) { m_pidController1.setI(i); kI_1 = i; }
//     if((d != kD_1)) { m_pidController1.setD(d); kD_1 = d; }
//     if((iz != kIz_1)) { m_pidController1.setIZone(iz); kIz_1 = iz; }
//     if((ff != kFF_1)) { m_pidController1.setFF(ff); kFF_1 = ff; }
//     if((max != kMaxOutput) || (min != kMinOutput)) { 
//       m_pidController1.setOutputRange(min, max);
//       kMinOutput = min; kMaxOutput = max; 
//     }


//     /*if((p != kP_2)) { m_pidController2.setP(p); kP_2 = p; }
//     if((i != kI_2)) { m_pidController2.setI(i); kI_2 = i; }
//     if((d != kD_2)) { m_pidController2.setD(d); kD_2 = d; }
//     if((iz != kIz_2)) { m_pidController2.setIZone(iz); kIz_2 = iz; }
//     if((ff != kFF_2)) { m_pidController2.setFF(ff); kFF_2 = ff; }
//     if((max != kMaxOutput) || (min != kMinOutput)) { 
//       m_pidController2.setOutputRange(min, max); 
//       kMinOutput = min; kMaxOutput = max; 
//     }*/

//     /**
//      * PIDController objects are commanded to a set point using the 
//      * SetReference() method.
//      * 
//      * The first parameter is the value of the set point, whose units vary
//      * depending on the control type set in the second parameter.
//      * 
//      * The second parameter is the control type can be set to one of four 
//      * parameters:
//      *  com.revrobotics.ControlType.kDutyCycle
//      *  com.revrobotics.ControlType.kPosition
//      *  com.revrobotics.ControlType.kVelocity
//      *  com.revrobotics.ControlType.kVoltage
//      */


//     m_pidController1.setReference((m_stick.getY() + 1.0) * 3.35/2.0, ControlType.kPosition); // shift the stick value to be from 0 to 3.3
//     SmartDashboard.putNumber("StickValue", (m_stick.getY() + 1.0) * 3.35/2.0);
//     SmartDashboard.putNumber("SetPoint", rotations);
//     SmartDashboard.putNumber("ProcessVariable", m_analogSensor1.getPosition());
//     //SmartDashboard.putNumber("ProcessVariable", m_encoder2.getPosition());
//   }

//   /** This function is called once each time the robot enters test mode. */
//   @Override
//   public void testInit() {}

//   /** This function is called periodically during test mode. */
//   @Override
//   public void testPeriodic() {}
// }
