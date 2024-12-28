import { signout } from "@/auth";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { P, s, v } from "@/types";

export default function Header() {
  return (
    <div className="flex justify-between items-center mb-6 bg-white">
      <h1 className="text-xl font-bold">
        Work<span className="text-[#8300E7]">Today</span>
      </h1>
      <Avatar />
    </div>
  );
}

function Avatar() {
  const navigate = useNavigate();
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const imageUrl = localStorage.getItem("imgUrl") ?? "";

  const toggleMenu = () => setIsMenuOpen(!isMenuOpen);
  const closeMenu = () => setIsMenuOpen(false);

  const menuOpts: { label: s; action: () => v | P<v> }[] = [
    window.location.pathname !== "/internProfile"
      ? { label: "Профиль", action: () => navigate("/internProfile") }
      : null,
    window.location.pathname !== "/internshipsSearch"
      ? {
          label: "Стажировки",
          action: () => navigate("/internshipsSearch"),
        }
      : null,
    { label: "Выйти", action: signout },
  ].filter((o): o is { label: s; action: () => v | P<v> } => Boolean(o));

  return (
    <div className="relative">
      <div
        className="rounded-xl w-12 h-12 bg-[#F3DFFF] cursor-pointer"
        style={{
          backgroundImage: `url(${imageUrl})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
        onClick={toggleMenu}
        onKeyUp={(e) => {
          if (e.key === "Enter" || e.key === " ") toggleMenu();
        }}
        tabIndex={0}
        role="button"
        aria-pressed={isMenuOpen}
      />

      {isMenuOpen && (
        <div
          className="absolute right-0 mt-2 w-40 bg-white border border-gray-200 rounded-lg shadow-lg"
          onMouseLeave={closeMenu}
        >
          <ul className="py-2">
            {menuOpts.map((option) => (
              <li
                key={option.label}
                onClick={() => {
                  option.action();
                  closeMenu();
                }}
                onKeyDown={(e) => {
                  if (e.key === "Enter" || e.key === " ") {
                    option.action();
                    closeMenu();
                  }
                }}
                className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
              >
                {option.label}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
