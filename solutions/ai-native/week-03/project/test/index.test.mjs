import assert from "node:assert/strict";import test from "node:test";import {canMerge} from "../src/index.mjs";
test("tests alone never authorize a protected change",()=>assert.deepEqual(canMerge({checks:[{status:"passed"}],files:["db/migrations/1.sql"],approval:null}),{ok:false,reason:"human_approval_required"}));
test("a normal change crosses both checks",()=>assert.deepEqual(canMerge({checks:[{status:"passed"}],files:["src/a.js"],approval:null}),{ok:true}));
