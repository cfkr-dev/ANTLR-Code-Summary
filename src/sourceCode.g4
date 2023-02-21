grammar sourceCode;

source: IDENTIFIER;

IDENTIFIER: '_'?MINUS;

fragment MAYUS: [A-Z];
fragment MINUS: [a-z];
fragment NUMBER: [0-9];
