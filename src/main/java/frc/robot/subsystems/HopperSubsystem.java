package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.HopperConstants;

/**
 * A hopper mechanism ran by a single {@link WPI_TalonSRX}.
 */
public class HopperSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_hopperMotor = new WPI_TalonSRX(HopperConstants.kHopperMotorCANPort);

  public HopperSubsystem(){
    m_hopperMotor.setNeutralMode(NeutralMode.Coast);
    m_hopperMotor.setInverted(true);
  }

  /**
   * Forward Hopper.
   */
  public void forwardHopper() {
    m_hopperMotor.set(ControlMode.PercentOutput,HopperConstants.kHopperMotorForwardSpeed);
  }

  /**
   * Reverse Hopper.
   */
  public void reverseHopper() {
    m_hopperMotor.set(ControlMode.PercentOutput,HopperConstants.kHopperMotorReverseSpeed);
  }

  /**
   * Stop Hopper.
   */
  public void stopHopper() {
    m_hopperMotor.set(ControlMode.PercentOutput,HopperConstants.kHopperMotorStopSpeed);
  }
}
