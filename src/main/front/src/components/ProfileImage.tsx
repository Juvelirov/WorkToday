import { v, s } from "@/types";
import { RefreshCcw, Trash } from "lucide-react";
import { useState } from "react";
import I from "./I";
import IB from "./IB";

interface ProfileImage {
  image: File | null;
  setImage: (image: File | null) => v;
  imageUrl: s;
}

export default function ProfileImage(p: ProfileImage) {
  const [hovered, setHovered] = useState(false);

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) p.setImage(selectedFile);
  };

  const removeImage = () => p.setImage(null);

  return (
    <div
      className="flex flex-col items-center justify-center w-60 h-60"
      onMouseEnter={() => setHovered(true)}
      onMouseLeave={() => setHovered(false)}
    >
      <div className="relative">
        {!p.image && !p.imageUrl ? (
          <label
            htmlFor="profile-image-upload"
            className="w-60 h-60 bg-cover rounded-3xl flex items-center bg-[#F3DFFF] justify-center cursor-pointer"
          >
            <span className="text-[#E4C1FF] text-6xl pb-3">+</span>
          </label>
        ) : (
          <div
            className="w-60 h-60 bg-cover rounded-3xl flex items-center bg-[#F3DFFF] justify-center"
            style={{
              backgroundImage: `url(${p.imageUrl})`,
              backgroundSize: "cover",
              backgroundPosition: "center",
            }}
          />
        )}
        <input
          type="file"
          id="profile-image-upload"
          className="hidden"
          accept="image/*"
          onChange={handleImageChange}
        />
        {(p.image || p.imageUrl) && hovered && (
          <div className="absolute bottom-[-20px] right-[90px] flex gap-1 p-1 bg-[#F3DFFF] rounded-lg">
            <label
              htmlFor="profile-image-upload"
              className="flex justify-center items-center w-full cursor-pointer"
            >
              <I icon={RefreshCcw} />
            </label>
            <IB icon={Trash} onClick={removeImage} bC="w-full text-red-500" />
          </div>
        )}
      </div>
      {!(p.image || p.imageUrl) && hovered && (
        <div className="absolute top-[180px] left-[180px] bg-[#E4C1FF] text-gray text-xs rounded-lg py-1 px-2 z-10 pointer-events-none">
          Загрузите изображение
        </div>
      )}
    </div>
  );
}
