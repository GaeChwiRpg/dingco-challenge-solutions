export function verifyRules(rules) {
  const required=["structure","verifyCommand","forbiddenPaths","completion"];
  const missing=required.filter(key=>!rules[key]||rules[key].length===0);
  return {ok:missing.length===0,missing,humanApproval:[...(rules.humanApproval||[])]};
}
