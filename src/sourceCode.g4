grammar sourceCode;



source: IDENTIFIER;

IDENTIFIER: (('_'+(MINUS|NUMBER)+(MINUS|NUMBER|'_')*)|(MINUS+(MINUS|NUMBER|'_')*));

fragment MAYUS: [A-Z];
fragment MINUS: [a-z];
fragment NUMBER: [0-9];
