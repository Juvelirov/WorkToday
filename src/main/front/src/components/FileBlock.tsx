import type { s } from "@/types";
import I from "./I";
import IB from "./IB";
import { Download, FileIcon, Trash } from "lucide-react";

interface FileBlock {
  name: s;
  setFile: React.Dispatch<React.SetStateAction<File | null>>;
}

export default function FileBlock(p: FileBlock) {
  return (
    <div className="flex justify-between items-center bg-[#F3DFFF] rounded-lg p-4">
      <div className="flex justify-between items-center gap-1">
        <I icon={FileIcon} />
        <p className="font-semibold">{p.name}</p>
      </div>
      <div className="flex items-center">
        <IB iC="p-1 rounded-full " bC="hover:bg-[#FFFFFF]" icon={Download} />
        <IB
          iC="p-1 rounded-full text-red-500"
          bC="hover:bg-[#FFFFFF]"
          icon={Trash}
          onClick={() => p.setFile(null)}
        />
      </div>
    </div>
  );
}
