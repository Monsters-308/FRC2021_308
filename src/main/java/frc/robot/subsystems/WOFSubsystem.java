package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.WOFConstants;

/**
 * A wof mechanism actuated by a single {@link WPI_TalonSRX}.
 */
public class WOFSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_wofMotor = new WPI_TalonSRX(WOFConstants.kWOFMotorCANPort);

  

  public WOFSubsystem(){
    m_wofMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic(){
  // SmartDashboard.putBoolean("Blue", WOF_Blue);
  // SmartDashboard.putBoolean("Red", WOF_Red);
  // SmartDashboard.putBoolean("Green", WOF_Green);
  // SmartDashboard.putBoolean("Yellow", detectcolor.Red);
  }
  /**
   * Forward WOF.
   */
  public void forwardWOF() {
    m_wofMotor.set(ControlMode.PercentOutput,WOFConstants.kWOFMotorForwardSpeed);
  }

  /**
   * Reverse WOF.
   */
  public void reverseWOF() {
    m_wofMotor.set(ControlMode.PercentOutput,WOFConstants.kWOFMotorReverseSpeed);
  }

  /**
   * Slow Forward WOF.
   */
  public void slowForwardWOF() {
    m_wofMotor.set(ControlMode.PercentOutput,WOFConstants.kWOFMotorSlowForwardSpeed);
  }

  /**
   * Slow Reverse WOF.
   */
  public void slowReverseWOF() {
    m_wofMotor.set(ControlMode.PercentOutput,WOFConstants.kWOFMotorSlowReverseSpeed);
  }

  /**
   * Stop WOF.
   */
  public void stopWOF() {
    m_wofMotor.set(ControlMode.PercentOutput,WOFConstants.kWOFMotorStopSpeed);
  }


}
