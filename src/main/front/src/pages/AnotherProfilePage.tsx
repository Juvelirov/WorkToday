import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchAnotherProfile } from "@/api/internAPI";
import { UsersInfoResponse } from "@/api/apiTypes";
import Header from "@/components/Header";
import { s } from "@/types";

export default function AnotherUserProfilePage() {
  const { email } = useParams<{ email: s }>();
  if (!email) throw new Error("No email");
  const [anotherProfile, setAnotherProfile] =
    useState<UsersInfoResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<s | null>(null);

  useEffect(() => {
    const loadProfile = async () => {
      setLoading(true);
      setError(null);
      try {
        const data = await fetchAnotherProfile(email);
        setAnotherProfile(data);
      } catch (e) {
        setError(`Failed to load profile: ${e}`);
      } finally {
        setLoading(false);
      }
    };

    loadProfile();
  }, [email]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div className="text-red-500">{error}</div>;
  if (!anotherProfile) return <div>No profile found.</div>;

  return (
    <div className="px-32 my-8">
      <Header />
      <div className="flex gap-16 mb-5">
        <div className="flex flex-col items-center justify-center gap-12">
          <div className="flex gap-8">
            <div
              className="w-60 h-60 bg-cover rounded-3xl flex items-center bg-[#F3DFFF] justify-center"
              style={{
                backgroundImage: `url(${anotherProfile.avatarPath})`,
                backgroundSize: "cover",
                backgroundPosition: "center",
              }}
            />
            <div className="flex flex-col justify-between">
              <div>
                <p className="font-bold text-sm mb-2">ФИО</p>
                <p className="bg-[#F3DFFF] w-[230px] p-3 rounded-md text-sm">
                  {anotherProfile.fio}
                </p>
              </div>
              <div>
                <p className="font-bold text-sm mb-2">Номер телефона</p>
                <p className="bg-[#F3DFFF] w-[230px] p-3 rounded-md text-sm">
                  {anotherProfile.phoneNumber}
                </p>
              </div>
              <div>
                <p className="font-bold text-sm mb-2">Город</p>
                <p className="bg-[#F3DFFF] w-[230px] p-3 rounded-md text-sm">
                  {anotherProfile.town}
                </p>
              </div>
            </div>
          </div>
        </div>
        <div className="flex flex-col flex-grow">
          <div className="flex flex-col">
            <h2 className="font-bold text-xl mb-4">Файлы</h2>
            {/* <div>
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
            </div> */}
          </div>
        </div>
      </div>
    </div>
  );
}
