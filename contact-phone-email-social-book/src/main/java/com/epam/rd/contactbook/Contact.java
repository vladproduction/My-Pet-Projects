package com.epam.rd.contactbook;

public class Contact {

    private static final String EPAM_COM = "epam.com";
    private String contactName;
    private final ContactInfo[] contactInfos;
    private int currentIndex;
    private boolean phoneAdded;
    private int totalEmailCount;
    private int totalSocialMediaCount;
    private final String[] titleArray;
    private int titleCount;

    public Contact(String contactName) {
        this.contactName = contactName;
        this.contactInfos = new ContactInfo[9];
        this.currentIndex = 0;
        this.phoneAdded = false;
        this.totalEmailCount = 0;
        this.totalSocialMediaCount = 0;
        this.titleArray = new String[5];
        this.titleCount = 0;
    }

    public void rename(String newName) {
        if (newName != null && !newName.isEmpty()) {
            contactName = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if (totalEmailCount < 3) {
            String result = localPart + "@" + domain;
            Email email = new Email(result);
            addContactInfo(email);
            totalEmailCount++;
            return email;
        }
        return null;
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if (totalEmailCount < 3) {
            String result = firstname + "_" + lastname + "@" + EPAM_COM;
            Email email = new Email(result) {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            addContactInfo(email);
            totalEmailCount++;
            return email;
        }
        return null;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (!phoneAdded) {
            ContactInfo contactInfo = new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }
                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
            };
            addContactInfo(contactInfo);
            phoneAdded = true;
            return contactInfo;
        }
        return null;
    }

    public Social addTwitter(String twitterId) {
        return addSocialMedia("Twitter", twitterId);
    }

    public Social addInstagram(String instagramId) {
        return addSocialMedia("Instagram", instagramId);
    }

    public Social addSocialMedia(String title, String id) {
        if (totalSocialMediaCount < 5) {
            Social social = new Social(title, id);
            addContactInfo(social);
            totalSocialMediaCount++;
            addTitle(title);
            return social;
        }
        return null;
    }

    private void addContactInfo(ContactInfo info) {
        if (currentIndex < contactInfos.length) {
            contactInfos[currentIndex++] = info;
        }
    }

    private void addTitle(String title) {
        if (titleCount < titleArray.length) {
            titleArray[titleCount++] = title;
        }
    }

    public ContactInfo[] getInfo() {
        int totalEntries = 1 + (phoneAdded ? 1 : 0) + totalEmailCount + totalSocialMediaCount;
        ContactInfo[] result = new ContactInfo[totalEntries];
        int resultIndex = 0;

        // Add Name
        result[resultIndex++] = new NameContactInfo();

        // Add Phone if exists
        if (phoneAdded) {
            for (ContactInfo info : contactInfos) {
                if (info != null && "Tel".equals(info.getTitle())) {
                    result[resultIndex++] = info;
                    break;
                }
            }
        }

        // Add Emails
        for (ContactInfo info : contactInfos) {
            if (info != null && (info.getTitle().equals("Email") || info.getTitle().equals("Epam Email"))) {
                result[resultIndex++] = info;
            }
        }

        // Add Social Media
        for (ContactInfo info : contactInfos) {
            if (info != null && isTitleInArray(info.getTitle())) {
                result[resultIndex++] = info;
            }
        }

        return result;
    }

    private boolean isTitleInArray(String title) {
        for (int i = 0; i < titleCount; i++) {
            if (titleArray[i].equals(title)) {
                return true;
            }
        }
        return false;
    }

    public static class Email implements ContactInfo {
        private final String email;
        public Email(String email) {
            this.email = email;
        }
        @Override
        public String getTitle() {
            return "Email";
        }
        @Override
        public String getValue() {
            return email;
        }
    }

    public static class Social implements ContactInfo {
        private final String socialId;
        private final String title;
        public Social(String title, String socialId) {
            this.title = title;
            this.socialId = socialId;
        }
        @Override
        public String getTitle() {
            return title != null ? title : "Social";
        }
        @Override
        public String getValue() {
            return socialId != null ? socialId : "";
        }
    }

    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }
        @Override
        public String getValue() {
            return contactName;
        }
    }

}
