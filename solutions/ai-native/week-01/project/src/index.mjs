export async function delegate(spec, model) {
  if (!spec?.input || !Array.isArray(spec.allowedPaths)) throw new Error("invalid_spec");
  if (spec.allowedPaths.some(path => path.includes(".."))) throw new Error("unsafe_path");
  const draft = await model({ input: spec.input, constraints: spec });
  const changed = draft.changedPaths || [];
  if (changed.some(path => !spec.allowedPaths.includes(path))) return { ok:false, stop:"scope_drift" };
  return { ok:true, draft };
}
