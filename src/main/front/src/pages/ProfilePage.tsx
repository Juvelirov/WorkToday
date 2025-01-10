import { EnrollResponse, UsersInfoResponse } from "@/api/apiTypes";
import {
  createPortfolio,
  createResume,
  deletePortfolio,
  deleteResume,
  fetchMyEnrolls,
  fetchMyProfile,
  updateProfile,
} from "@/api/internAPI";
import Header from "@/components/Header";
import I from "@/components/I";
import ProfileImage from "@/components/ProfileImage";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { WFile } from "@/components/WFile";
import { F, s, v } from "@/types";
import { ScrollTextIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export function InternProfilePage() {
  const [refreshKey, setRefreshKey] = useState(0);
  const [profileD, setProfileD] = useState<UsersInfoResponse | undefined>(
    undefined
  );
  const [myEnrolls, setMyEnrolls] = useState<EnrollResponse[]>([]);

  useEffect(() => {
    const loadProfile = async () => {
      const profileD = await fetchMyProfile();
      console.log(profileD);
      setProfileD(profileD);
    };

    loadProfile();
  }, [refreshKey]);

  useEffect(() => {
    const loadMyEnrolls = async () => {
      const myEnrolls = await fetchMyEnrolls();
      console.log(myEnrolls);
      setMyEnrolls(myEnrolls);
    };

    loadMyEnrolls();
  }, []);

  const refreshPage = () => setRefreshKey((prevKey) => ++prevKey);

  if (!profileD) return <div>Loading...</div>;
  return (
    <ProfileBase
      role="intern"
      profileD={profileD}
      onRefresh={refreshPage}
      myEnrolls={myEnrolls}
    />
  );
}

// export function HrProfilePage() {
//   return <ProfileBase role="hr" />;
// }

interface ProfileBase {
  role: "intern" | "hr";
  profileD: UsersInfoResponse;
  onRefresh: F<v>;
  myEnrolls: EnrollResponse[];
}

function ProfileBase(p: ProfileBase) {
  const [avatar, setAvatar] = useState<File | undefined>(undefined);
  const [avatarPath, setAvatarPath] = useState<s>(
    localStorage.getItem("imgUrl") ?? p.profileD.avatarPath ?? ""
  );
  const [fio, setFio] = useState(p.profileD.fio ?? "");
  const [phoneNumber, setPhoneNumber] = useState(p.profileD.phoneNumber ?? "");
  const [town, setTown] = useState(p.profileD.town ?? "");

  const [isChanged, setIsChanged] = useState(false);

  const handleInputChange = (
    setter: React.Dispatch<React.SetStateAction<s>>
  ) => {
    return (e: React.ChangeEvent<HTMLInputElement>) => {
      setter(e.target.value);
      setIsChanged(true);
    };
  };

  const handleImageUpload = (file: File | undefined) => {
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64Image = reader.result as s;
        localStorage.setItem("imgUrl", avatarPath);

        setAvatar(file);
        setAvatarPath(base64Image);
        setIsChanged(true);
      };
      reader.readAsDataURL(file);
    } else {
      localStorage.removeItem("imgUrl");

      setAvatar(undefined);
      setAvatarPath("");
      setIsChanged(true);
    }
  };

  const handleSave = async () => {
    try {
      await updateProfile({
        avatar,
        fio,
        phoneNumber,
        town,
      });

      setIsChanged(false);
    } catch (error) {
      console.error("Failed to update profile:", error);
    }
  };

  if (!localStorage.getItem("imgUrl") && p.profileD.avatarPath) {
    localStorage.setItem("imgUrl", p.profileD.avatarPath);
    setAvatarPath(p.profileD.avatarPath);
  }

  return (
    <div className="px-32 my-8">
      <Header />
      <div className="flex gap-16 mb-5">
        <div className="flex flex-col items-center justify-center gap-12">
          <div className="flex gap-8">
            <ProfileImage
              avatar={avatar}
              setAvatar={handleImageUpload}
              avatarPath={avatarPath}
            />
            <div className="flex flex-col justify-between">
              <div>
                <p className="font-bold text-sm mb-2">ФИО</p>
                <Input
                  className="placeholder:text-gray-600 bg-[#F3DFFF] w-[230px]"
                  value={fio}
                  onChange={handleInputChange(setFio)}
                  placeholder="Введите ваше ФИО"
                />
              </div>
              <div>
                <p className="font-bold text-sm mb-2">Номер телефона</p>
                <Input
                  className="placeholder:text-gray-600 bg-[#F3DFFF]"
                  value={phoneNumber}
                  onChange={handleInputChange(setPhoneNumber)}
                  placeholder="Введите ваш номер"
                />
              </div>
              <div>
                <p className="font-bold text-sm mb-2">Город</p>
                <Input
                  className="placeholder:text-gray-600 bg-[#F3DFFF]"
                  value={town}
                  onChange={handleInputChange(setTown)}
                  placeholder="Введите ваш город"
                />
              </div>
            </div>
          </div>
          {isChanged && (
            <Button
              className="bg-[#F3DFFF] text-black rounded-full hover:bg-[#F3DFFF]"
              onClick={handleSave}
            >
              Сохранить изменения
            </Button>
          )}
        </div>
        <div className="flex flex-col flex-grow">
          <div className="flex flex-col">
            <h2 className="font-bold text-xl mb-4">Файлы</h2>
            <div>
              <WFile
                title="Портфолио"
                file={p.profileD.portfolio}
                onUpload={createPortfolio}
                onDelete={deletePortfolio}
                onRefresh={p.onRefresh}
              />
              <WFile
                title="Резюме"
                file={p.profileD.resume}
                onUpload={createResume}
                onDelete={deleteResume}
                onRefresh={p.onRefresh}
              />
            </div>
          </div>
          {p.role === "hr" && (
            <div>
              <h2 className="font-bold text-xl mb-10">Ссылки</h2>
              <div className="flex justify-evenly gap-5">
                <Link to="/analytics">
                  <div className="flex items-center gap-4 rounded-2xl p-4 bg-[#F3DFFF]">
                    <I icon={ScrollTextIcon} />
                    <p>Аналитика</p>
                  </div>
                </Link>
              </div>
            </div>
          )}
        </div>
      </div>
      {p.myEnrolls.length > 0 && (
        <div>
          <h2 className="font-bold text-xl">Мои стажировки</h2>
          {p.myEnrolls.map((e) => (
            <div key={e.enrollId}>huh</div>
          ))}
        </div>
      )}
    </div>
  );
}
