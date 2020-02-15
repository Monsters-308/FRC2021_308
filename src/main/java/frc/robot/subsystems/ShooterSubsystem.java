package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/**
 * A shooter mechanism ran by a single {@link WPI_TalonFX}.
 */
public class ShooterSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_shooterMotor = new WPI_TalonFX(ShooterConstants.kShooterMotorCANPort);

  public ShooterSubsystem(){
    m_shooterMotor.configFactoryDefault();
    m_shooterMotor.setNeutralMode(NeutralMode.Coast);
    m_shooterMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40.0, 45.0, 0.5));
    
  }

  /**
   * Forward Shooter.
   */
  public void forwardShooter() {
    m_shooterMotor.set(ControlMode.PercentOutput,ShooterConstants.kShooterMotorForwardSpeed);
  }

  /**
   * Reverse Shooter.
   */
  public void reverseShooter() {
    m_shooterMotor.set(ControlMode.PercentOutput,ShooterConstants.kShooterMotorReverseSpeed);
  }

  /**
   * Stop Shooter.
   */
  public void stopShooter() {
    m_shooterMotor.set(ControlMode.PercentOutput,ShooterConstants.kShooterMotorStopSpeed);
  }
}
