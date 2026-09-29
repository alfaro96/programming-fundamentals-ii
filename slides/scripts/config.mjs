// Reads reveal-md.json5 for the other scripts, which import readConfig() or run this one
//
// size: prints the canvas width and height in pixels, in the form reveal-md --print-size takes

import { readFile } from "node:fs/promises";

// Plain JSON once the // comments are dropped: keep the file to JSON plus comments (no trailing commas)
export async function readConfig() {
  const text = await readFile("reveal-md.json5", "utf8");
  let json = "";
  let inString = false;
  for (let i = 0; i < text.length; i++) {
    const c = text[i];
    if (inString) {
      json += c;
      if (c === "\\") json += text[++i];
      else if (c === '"') inString = false;
    } else if (c === '"') {
      inString = true;
      json += c;
    } else if (c === "/" && text[i + 1] === "/") {
      while (i < text.length && text[i] !== "\n") i++;
      json += "\n";
    } else {
      json += c;
    }
  }
  return JSON.parse(json);
}

if (process.argv[2] === "size") {
  const { width, height } = (await readConfig()).revealOptions;
  console.log(`${width}x${height}px`);
}
