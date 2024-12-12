export type s = string;
export type n = number;
export type b = boolean;

export type ss = string[];
export type nn = number[];

// biome-ignore lint/suspicious/noConfusingVoidType: <explanation>
export type v = void;

export type Pb = Promise<b>;
export type Ps = Promise<s>;
export type Pn = Promise<n>;
export type Pv = Promise<v>;
