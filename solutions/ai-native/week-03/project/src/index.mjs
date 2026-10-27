export function canMerge({checks,files,approval}) {
  const protectedChange=files.some(path=>/migrations|auth|secrets/.test(path));
  if (checks.some(check=>check.status!=="passed")) return {ok:false,reason:"checks"};
  if (protectedChange && approval!=="human") return {ok:false,reason:"human_approval_required"};
  return {ok:true};
}
