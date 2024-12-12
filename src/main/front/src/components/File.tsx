import type { s } from "@/types";
import I from "./I";
import IB from "./IB";

interface File {
  name: s;
}

export default function File(p: File) {
  return (
    <div className="flex justify-between items-center bg-[#F3DFFF] rounded-lg p-4">
      <div className="flex justify-between items-center gap-1">
        <I name="description" />
        <p className="font-semibold">{p.name}</p>
      </div>
      <div className="flex items-center gap-1">
        <IB
          iC="p-1 rounded-full "
          bC="hover:bg-[#FFFFFF]"
          nameOutlined="file_download"
        />
        <IB
          iC="p-1 rounded-full text-red-500"
          bC="hover:bg-[#FFFFFF]"
          nameOutlined="delete"
        />
      </div>
    </div>
  );
}
