import assert from "node:assert/strict";import test from "node:test";import {delegate} from "../src/index.mjs";
test("stops when the model leaves the delegated boundary",async()=>{const result=await delegate({input:"normalize",allowedPaths:["a.md"]},async()=>({changedPaths:["a.md","secret.env"]}));assert.deepEqual(result,{ok:false,stop:"scope_drift"});});
test("accepts a bounded draft",async()=>{const result=await delegate({input:"normalize",allowedPaths:["a.md"]},async()=>({changedPaths:["a.md"]}));assert.equal(result.ok,true);});
