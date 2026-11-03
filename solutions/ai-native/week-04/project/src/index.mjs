export async function operate({plan,accept,deploy,observe,rollback}) {
  const accepted=await accept(plan);
  if (!accepted.ok) return {state:"rejected",metrics:accepted.metrics};
  const release=await deploy(accepted.artifact);
  const health=await observe(release);
  if (!health.ok) { await rollback(release); return {state:"rolled_back",metrics:health.metrics}; }
  return {state:"healthy",metrics:health.metrics};
}
