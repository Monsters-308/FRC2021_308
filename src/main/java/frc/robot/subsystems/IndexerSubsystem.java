package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IndexerConstants;

/**
 * A indexer mechanism actuated by a single {@link WPI_TalonSRX}.
 */
public class IndexerSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_indexerMotor = new WPI_TalonSRX(IndexerConstants.kIndexerMotorCANPort);


  public IndexerSubsystem(){
    m_indexerMotor.setNeutralMode(NeutralMode.Brake);
    m_indexerMotor.setInverted(true);
  }

  /**
   * Forward Indexer.
   */
  public void forwardIndexer() {
    m_indexerMotor.set(ControlMode.PercentOutput,IndexerConstants.kIndexerMotorForwardSpeed);
  }

  /**
   * Reverse Indexer.
   */
  public void reverseIndexer() {
    m_indexerMotor.set(ControlMode.PercentOutput,IndexerConstants.kIndexerMotorReverseSpeed);
  }

  /**
   * Stop Indexer.
   */
  public void stopIndexer() {
    m_indexerMotor.set(ControlMode.PercentOutput,IndexerConstants.kIndexerMotorStopSpeed);
  }


}
