import type { s } from "@/types";
import I from "./I";
import { useState } from "react";

interface IB {
  nameFilled: s;
  nameOutlined: s;
  c?: s;
  onClick?: () => void;
}

export default function IB(p: IB) {
  const [isFilled, setIsFilled] = useState(false);

  return (
    <button
      type="button"
      className="inline-flex justify-center items-center p-1 rounded-full hover:bg-[#F3DFFF]"
      onClick={() => {
        setIsFilled(!isFilled);
        if (p.onClick) p.onClick();
      }}
    >
      <I c={p.c} name={isFilled ? p.nameFilled : p.nameOutlined} />
    </button>
  );
}
