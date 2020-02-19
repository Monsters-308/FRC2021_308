package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.TraverseConstants;

/**
 * A traverse mechanism actuated by a single {@link WPI_TalonSRX}.
 */
public class TraverseSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_traverseMotor = new WPI_TalonSRX(TraverseConstants.kTraverseMotorCANPort);


  public TraverseSubsystem(){
    m_traverseMotor.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * Forward Traverse.
   */
  public void forwardTraverse() {
    m_traverseMotor.set(ControlMode.PercentOutput,TraverseConstants.kTraverseMotorForwardSpeed);
  }

  /**
   * Reverse Traverse.
   */
  public void reverseTraverse() {
    m_traverseMotor.set(ControlMode.PercentOutput,TraverseConstants.kTraverseMotorReverseSpeed);
  }

  /**
   * Stop Traverse.
   */
  public void stopTraverse() {
    m_traverseMotor.set(ControlMode.PercentOutput,TraverseConstants.kTraverseMotorStopSpeed);
  }


}
