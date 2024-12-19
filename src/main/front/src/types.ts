export type s = string;
export type n = number;
export type b = boolean;

export type ss = string[];
export type nn = number[];

// biome-ignore lint/suspicious/noConfusingVoidType: <explanation>
export type v = void;

export type Fv = () => v;
export type F<T> = (arg: T) => v;

export type P<T> = Promise<T>;
