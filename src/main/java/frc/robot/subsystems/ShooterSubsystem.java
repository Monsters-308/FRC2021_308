package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;

/**
 * A shooter mechanism ran by a single {@link WPI_TalonFX}.
 */
public class ShooterSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_shooterMotor = new WPI_TalonFX(ShooterConstants.kShooterMotorCANPort);
  private TalonFXConfiguration m_configs = new TalonFXConfiguration();

  public ShooterSubsystem(){
    m_shooterMotor.configFactoryDefault();
    m_configs.openloopRamp = 3.0;
    m_configs.statorCurrLimit = new StatorCurrentLimitConfiguration(true,40.0,45.0,1.0);
    m_configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    m_configs.nominalOutputForward = 0.0;
    m_configs.nominalOutputReverse = 0.0;
    m_configs.peakOutputForward = 1.0;
    m_configs.peakOutputReverse = 0.0; // dont allow reverse
    
    m_shooterMotor.configAllSettings(m_configs);
    m_shooterMotor.config_kF(0,Constants.ShooterConstants.kF);
    m_shooterMotor.config_kD(0,Constants.ShooterConstants.kD);
    m_shooterMotor.config_kP(0,Constants.ShooterConstants.kP);
    m_shooterMotor.config_kI(0,Constants.ShooterConstants.kI);

    m_shooterMotor.setNeutralMode(NeutralMode.Coast);
    m_shooterMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0,20);
  }

  /**
   * Forward Shooter.
   */
  public void forwardShooter() {
    m_shooterMotor.set(ControlMode.PercentOutput,ShooterConstants.kShooterMotorForwardSpeed);
  }

  public void speedControlShooter(double speedTarget){
    double sensorVelocityTarget = speedTarget * Constants.ShooterConstants.kUnitsPerRevolution / 600.0;
    m_shooterMotor.set(ControlMode.Velocity,sensorVelocityTarget);
  }
  public double getShooterVelocity(){
    int sensorVelocity = m_shooterMotor.getSelectedSensorVelocity(0);
    double vel_RotPerSec = (double)sensorVelocity / Constants.ShooterConstants.kUnitsPerRevolution * 10;
    double vel_RotPerMin = vel_RotPerSec * 60.0;

    return vel_RotPerMin;

  }

  /**
   * Reverse Shooter.
   */
  public void shortShooter() {
    m_shooterMotor.set(ControlMode.PercentOutput,ShooterConstants.kShooterMotorShortSpeed);
  }

  /**
   * Stop Shooter.
   */
  public void stopShooter() {
    m_shooterMotor.set(ControlMode.PercentOutput,ShooterConstants.kShooterMotorStopSpeed);
  }

  @Override
  public void periodic(){
    //TODO add a loop counter in here
    SmartDashboard.putNumber("Current", m_shooterMotor.getStatorCurrent());
    SmartDashboard.putNumber("Temp",m_shooterMotor.getTemperature());
    SmartDashboard.putNumber("RPM",getShooterVelocity());
  }
}
