import assert from "node:assert/strict";import test from "node:test";import {verifyRules} from "../src/index.mjs";
test("fails closed when an operating boundary is missing",()=>assert.deepEqual(verifyRules({structure:"src",verifyCommand:"npm test",forbiddenPaths:[".env"]}),{ok:false,missing:["completion"],humanApproval:[]}));
test("preserves human approval gates",()=>assert.deepEqual(verifyRules({structure:"src",verifyCommand:"npm test",forbiddenPaths:[".env"],completion:"green",humanApproval:["deploy"]}).humanApproval,["deploy"]));
