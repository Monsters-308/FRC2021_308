package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IndexerSubsystem;

/**
 * A simple command that reverses the indexer with the {@link IndexerSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ReverseIndexer extends CommandBase {
  // The subsystem the command runs on
  private final IndexerSubsystem m_indexerSubsystem;

  public ReverseIndexer(IndexerSubsystem subsystem) {
    m_indexerSubsystem = subsystem;
    addRequirements(m_indexerSubsystem);
  }

  @Override
  public void initialize() {
    m_indexerSubsystem.reverseIndexer();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
