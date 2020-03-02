package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final CANSparkMax m_leftFront = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
  private final CANSparkMax m_leftRear = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
  // The motors on the right side of the drive.
  private final CANSparkMax m_rightFront = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
  private final CANSparkMax m_rightRear = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);
  
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftFront,m_leftRear);

  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightFront,m_rightRear);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The left-side drive encoder
  private final CANEncoder m_leftEncoder = m_leftFront.getEncoder();

  // The right-side drive encoder
  private final CANEncoder m_rightEncoder = m_rightFront.getEncoder();

  // The Gyro
  private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

  private final Solenoid m_visionSolenoid = new Solenoid(Constants.DriveConstants.kLEDRingModule,Constants.DriveConstants.kLEDRingPort);

  private final NetworkTable m_visionTable;
  private final NetworkTableEntry m_visionYaw;
  private final NetworkTableEntry m_isDriverMode;
  private final NetworkTableEntry m_isValid;
  private final NetworkTableEntry m_pose;
 
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    m_leftFront.restoreFactoryDefaults();
    m_leftRear.restoreFactoryDefaults();
    m_rightFront.restoreFactoryDefaults();
    m_rightRear.restoreFactoryDefaults();
    m_leftFront.setSmartCurrentLimit(40, 40);
    m_leftRear.setSmartCurrentLimit(40, 40);
    m_rightFront.setSmartCurrentLimit(40, 40);
    m_rightRear.setSmartCurrentLimit(40, 40);
    m_leftRear.follow(m_leftFront);
    m_rightRear.follow(m_rightFront);

    // Sets the distance per pulse for the encoders
    m_leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    m_visionTable = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("MyCamName");
    m_visionYaw = m_visionTable.getEntry("yaw");
    m_isDriverMode = m_visionTable.getEntry("driver_mode");
    m_isValid = m_visionTable.getEntry("is_Valid");
    m_pose = m_visionTable.getEntry("poseList");

    m_isDriverMode.setBoolean(true);//set the driver mode
    m_gyro.calibrate();
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_leftEncoder.setPosition(0.0);
    m_rightEncoder.setPosition(0.0);
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getPosition() + m_rightEncoder.getPosition()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public CANEncoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public CANEncoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  public void setLedState(boolean state){
    m_visionSolenoid.set(state);
  }

  public double getVisionYaw(){
    return m_visionYaw.getDouble(0.0);
  }

  public boolean isValid(){
    return m_isValid.getBoolean(false);
  }

  public double getVisonPose(){
    double[] retval = {0.0,0.0,0,0};
    retval = m_pose.getDoubleArray(retval);
    return retval[0];
  }

  public void setDriverMode(boolean state){
    m_isDriverMode.setBoolean(state);
  }

  public double getGyroHeading(){
    return m_gyro.getAngle();
  }

  public void resetGyro(){
    m_gyro.reset();
  }

  @Override
  public void periodic(){
    // here is a place to send to the smartdashboard
    SmartDashboard.putNumber("LeftEncoder",m_leftEncoder.getPosition());
    SmartDashboard.putNumber("LeftVelocity",m_leftEncoder.getVelocity());
    SmartDashboard.putNumber("RightEncoder",m_rightEncoder.getPosition());
    SmartDashboard.putNumber("RightVelocity",m_rightEncoder.getVelocity());

    SmartDashboard.putNumber("VisionYaw",m_visionYaw.getDouble(0.0));
    SmartDashboard.putBoolean("VisionValid",m_isValid.getBoolean(false));
    SmartDashboard.putNumber("VisionPose",getVisonPose());
    
    SmartDashboard.putNumber("GyroHeading",m_gyro.getAngle());
  }


}
